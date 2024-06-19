package br.infnet.musicfun.domain.payment.controller;

import br.infnet.musicfun.domain.payment.dto.SubscriptionDTO;
import br.infnet.musicfun.domain.payment.model.Subscription;
import br.infnet.musicfun.domain.payment.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping
    public List<SubscriptionDTO> getAllSubscriptions() {
        return subscriptionService.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionDTO> getSubscriptionById(@PathVariable Long id) {
        return subscriptionService.findById(id).map(subscription -> ResponseEntity.ok(convertToDTO(subscription)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SubscriptionDTO> createSubscription(@RequestBody SubscriptionDTO subscriptionDTO) {
        Subscription subscription = convertToEntity(subscriptionDTO);
        Subscription savedSubscription = subscriptionService.save(subscription);
        return ResponseEntity.ok(convertToDTO(savedSubscription));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable Long id) {
        subscriptionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private SubscriptionDTO convertToDTO(Subscription subscription) {
        return SubscriptionDTO.builder()
                .id(subscription.getId())
                .startDate(subscription.getStartDate())
                .endDate(subscription.getEndDate())
                .status(subscription.getStatus())
                .build();
    }

    private Subscription convertToEntity(SubscriptionDTO subscriptionDTO) {
        return Subscription.builder()
                .startDate(subscriptionDTO.getStartDate())
                .endDate(subscriptionDTO.getEndDate())
                .status(subscriptionDTO.getStatus())
                .build();
    }
}
