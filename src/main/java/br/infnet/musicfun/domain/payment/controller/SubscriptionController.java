package br.infnet.musicfun.domain.payment.controller;

import br.infnet.musicfun.domain.payment.dto.SubscriptionDTO;
import br.infnet.musicfun.domain.payment.model.Subscription;
import br.infnet.musicfun.domain.payment.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping
    public List<SubscriptionDTO> getAllSubscriptions() {
        return subscriptionService.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionDTO> getSubscriptionById(@PathVariable Long id) {
        Optional<SubscriptionDTO> subscriptionDTO = subscriptionService.findById(id)
                .map(this::convertToDTO);
        return subscriptionDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SubscriptionDTO> createSubscription(@RequestBody SubscriptionDTO subscriptionDTO) {
        Subscription subscription = convertToEntity(subscriptionDTO);
        Subscription savedSubscription = subscriptionService.save(subscription);
        SubscriptionDTO responseDTO = convertToDTO(savedSubscription);
        return ResponseEntity.status(201).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubscriptionDTO> updateSubscription(@PathVariable Long id, @RequestBody SubscriptionDTO subscriptionDTO) {
        Subscription subscription = convertToEntity(subscriptionDTO);
        subscription.setId(id);
        Subscription updatedSubscription = subscriptionService.update(subscription);
        SubscriptionDTO responseDTO = convertToDTO(updatedSubscription);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable Long id) {
        subscriptionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private SubscriptionDTO convertToDTO(Subscription subscription) {
        return new SubscriptionDTO(
                subscription.getId(),
                subscription.getPlanName(),
                subscription.getDuration(),
                subscription.getPrice(),
                subscription.getStartDate(),
                subscription.getEndDate(),
                subscription.getType()
        );
    }

    private Subscription convertToEntity(SubscriptionDTO subscriptionDTO) {
        Subscription subscription = new Subscription();
        subscription.setId(subscriptionDTO.getId());
        subscription.setPlanName(subscriptionDTO.getPlanName());
        subscription.setPrice(subscriptionDTO.getPrice());
        subscription.setStartDate(subscriptionDTO.getStartDate());
        subscription.setEndDate(subscriptionDTO.getEndDate());
        subscription.setType(subscriptionDTO.getType());
        return subscription;
    }
}
