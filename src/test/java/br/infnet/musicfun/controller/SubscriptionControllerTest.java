package br.infnet.musicfun.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import br.infnet.musicfun.domain.payment.dto.SubscriptionDTO;
import br.infnet.musicfun.domain.payment.model.Subscription;
import br.infnet.musicfun.domain.payment.repository.SubscriptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
public class SubscriptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    private Long testSubscriptionId;

    @BeforeEach
    public void setup() {
        subscriptionRepository.deleteAll(); // Clear existing data

        Subscription testSubscription = new Subscription();
        testSubscription.setPlanName("Basic Plan");
        testSubscription.setPrice(9.99);
        testSubscription.setStartDate(LocalDate.now());
        testSubscription.setEndDate(LocalDate.now().plusDays(30));
        testSubscription.setType("Monthly");

        Subscription savedSubscription = subscriptionRepository.save(testSubscription);
        testSubscriptionId = savedSubscription.getId();
    }

    @Test
    public void createSubscriptionTest() throws Exception {
        SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
        subscriptionDTO.setPlanName("New Plan");
        subscriptionDTO.setPrice(19.99);
        subscriptionDTO.setStartDate(LocalDate.now());
        subscriptionDTO.setEndDate(LocalDate.now().plusDays(60));
        subscriptionDTO.setType("Yearly");

        mockMvc.perform(post("/subscriptions")
                .with(csrf())
                .with(user("user").password("password").roles("USER"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(subscriptionDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.planName").value("New Plan"))
                .andExpect(jsonPath("$.price").value(19.99))
                .andExpect(jsonPath("$.type").value("Yearly"));
    }

    @Test
    public void getSubscriptionByIdTest() throws Exception {
        mockMvc.perform(get("/subscriptions/{id}", testSubscriptionId)
                .with(user("user").password("password").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testSubscriptionId))
                .andExpect(jsonPath("$.planName").value("Basic Plan"))
                .andExpect(jsonPath("$.price").value(9.99))
                .andExpect(jsonPath("$.startDate").exists())
                .andExpect(jsonPath("$.endDate").exists())
                .andExpect(jsonPath("$.type").value("Monthly"));
    }

    @Test
    public void getAllSubscriptionsTest() throws Exception {
        mockMvc.perform(get("/subscriptions")
                .with(user("user").password("password").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].planName").value("Basic Plan"))
                .andExpect(jsonPath("$[0].price").value(9.99));
    }

    @Test
    public void updateSubscriptionTest() throws Exception {
        SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
        subscriptionDTO.setPlanName("Updated Plan");
        subscriptionDTO.setPrice(19.99);
        subscriptionDTO.setStartDate(LocalDate.now());
        subscriptionDTO.setEndDate(LocalDate.now().plusDays(60));
        subscriptionDTO.setType("Yearly");

        mockMvc.perform(put("/subscriptions/{id}", testSubscriptionId)
                .with(csrf())
                .with(user("user").password("password").roles("USER"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(subscriptionDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.planName").value("Updated Plan"))
                .andExpect(jsonPath("$.price").value(19.99))
                .andExpect(jsonPath("$.type").value("Yearly"));
    }

    @Test
    public void deleteSubscriptionTest() throws Exception {
        mockMvc.perform(delete("/subscriptions/{id}", testSubscriptionId)
                .with(csrf())
                .with(user("user").password("password").roles("USER")))
                .andExpect(status().isNoContent());
    }
}
