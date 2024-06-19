package br.infnet.musicfun.domain.payment.repository;

import br.infnet.musicfun.domain.payment.model.Transaction;
import br.infnet.musicfun.domain.core.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends BaseRepository<Transaction> {
    List<Transaction> findByUserIdAndTransactionDateBetween(Long userId, LocalDateTime start, LocalDateTime end);
}
