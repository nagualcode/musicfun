package br.infnet.musicfun.domain.payment.service;

import br.infnet.musicfun.domain.payment.model.Subscription;
import br.infnet.musicfun.domain.payment.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public List<Subscription> findAll() {
        return subscriptionRepository.findAll();
    }

    public Optional<Subscription> findById(Long id) {
        return subscriptionRepository.findById(id);
    }

    public Subscription save(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    public Subscription update(Subscription subscription) {
        return subscriptionRepository.save(subscription); // save method works as update if the ID exists
    }

    public void delete(Long id) {
        subscriptionRepository.deleteById(id);
    }
}
