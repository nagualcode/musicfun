package br.infnet.musicfun.controller;

import br.infnet.musicfun.domain.payment.dto.TransactionDTO;
import br.infnet.musicfun.domain.payment.model.Transaction;
import br.infnet.musicfun.domain.payment.repository.TransactionRepository;
import br.infnet.musicfun.domain.payment.service.TransactionService;
import br.infnet.musicfun.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TransactionRepository transactionRepository;

    @MockBean
    private TransactionService transactionService;

    private Long testTransactionId;
    private TransactionDTO transactionDTO;

    @BeforeEach
    public void setUp() {
        transactionRepository.deleteAll(); // Clear existing data

        Transaction testTransaction = new Transaction();
        testTransaction.setAmount(15.00);
        testTransaction.setSubscriptionId(1L);
        testTransaction.setMerchant("Merchant1");
        testTransaction.setStatus("Success");
        testTransaction.setTimestamp(LocalDateTime.now());

        Transaction savedTransaction = transactionRepository.save(testTransaction);
        testTransactionId = savedTransaction.getId();

        transactionDTO = new TransactionDTO(savedTransaction.getId(), savedTransaction.getAmount(),
                savedTransaction.getSubscriptionId(), savedTransaction.getMerchant(),
                savedTransaction.getStatus(), savedTransaction.getTimestamp());
    }

    @Test
    public void createTransactionTest() throws Exception {
        TransactionDTO newTransactionDTO = new TransactionDTO(null, 20.00, 2L, "Merchant2", "Pending", LocalDateTime.now());

        when(transactionService.save(any(TransactionDTO.class))).thenReturn(newTransactionDTO);

        mockMvc.perform(post("/transactions")
                .with(csrf())
                .with(user("user").password("password").roles("USER"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newTransactionDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.amount").value(20.00))
                .andExpect(jsonPath("$.subscriptionId").value(2L))
                .andExpect(jsonPath("$.merchant").value("Merchant2"))
                .andExpect(jsonPath("$.status").value("Pending"));
    }

    @Test
    public void getTransactionByIdTest() throws Exception {
        when(transactionService.findById(testTransactionId)).thenReturn(transactionDTO);

        mockMvc.perform(get("/transactions/{id}", testTransactionId)
                .with(user("user").password("password").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testTransactionId))
                .andExpect(jsonPath("$.amount").value(15.00))
                .andExpect(jsonPath("$.subscriptionId").value(1L))
                .andExpect(jsonPath("$.merchant").value("Merchant1"))
                .andExpect(jsonPath("$.status").value("Success"))
                .andExpect(jsonPath("$.timestamp").exists());
    }

    @Test
    public void getAllTransactionsTest() throws Exception {
        List<TransactionDTO> transactions = Collections.singletonList(transactionDTO);
        when(transactionService.findAll()).thenReturn(transactions);

        mockMvc.perform(get("/transactions")
                .with(user("user").password("password").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].amount").value(15.00));
    }

    @Test
    public void updateTransactionTest() throws Exception {
        TransactionDTO updatedTransactionDTO = new TransactionDTO(testTransactionId, 25.00, 2L, "Merchant2", "Completed", LocalDateTime.now());

        when(transactionService.update(any(Long.class), any(TransactionDTO.class))).thenReturn(updatedTransactionDTO);

        mockMvc.perform(put("/transactions/{id}", testTransactionId)
                .with(csrf())
                .with(user("user").password("password").roles("USER"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedTransactionDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(25.00))
                .andExpect(jsonPath("$.subscriptionId").value(2L))
                .andExpect(jsonPath("$.merchant").value("Merchant2"))
                .andExpect(jsonPath("$.status").value("Completed"));
    }

    @Test
    public void deleteTransactionTest() throws Exception {
        mockMvc.perform(delete("/transactions/{id}", testTransactionId)
                .with(csrf())
                .with(user("user").password("password").roles("USER")))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetTransactionById_NotFound() throws Exception {
        when(transactionService.findById(testTransactionId)).thenThrow(new ResourceNotFoundException("Transaction not found"));

        mockMvc.perform(get("/transactions/{id}", testTransactionId)
                .with(user("user").password("password").roles("USER")))
                .andExpect(status().isNotFound());
    }
}
