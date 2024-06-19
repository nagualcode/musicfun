package br.infnet.musicfun.domain.payment.repository;

import br.infnet.musicfun.domain.core.repository.BaseRepository;
import br.infnet.musicfun.domain.payment.model.Subscription;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends BaseRepository<Subscription> {
}
