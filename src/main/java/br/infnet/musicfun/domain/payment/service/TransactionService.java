package br.infnet.musicfun.domain.payment.service;

import br.infnet.musicfun.domain.payment.model.Transaction;
import br.infnet.musicfun.domain.payment.repository.TransactionRepository;
import br.infnet.musicfun.domain.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService extends BaseService<Transaction> {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AntiFraudService antiFraudService;

    @Override
    public Transaction save(Transaction transaction) {
        if (antiFraudService.validateTransaction(transaction)) {
            return transactionRepository.save(transaction);
        } else {
            throw new RuntimeException("Transaction is fraudulent");
        }
    }

    // Additional methods related to transactions can be added here
}
