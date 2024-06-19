package br.infnet.musicfun.domain.payment.service;

import br.infnet.musicfun.domain.payment.model.Subscription;
import br.infnet.musicfun.domain.payment.repository.SubscriptionRepository;
import br.infnet.musicfun.domain.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService extends BaseService<Subscription> {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    // Additional methods related to subscriptions can be added here
}
