package br.infnet.musicfun.domain.payment.service;

import br.infnet.musicfun.domain.payment.model.Transaction;
import org.springframework.stereotype.Service;

@Service
public class AntiFraudService {

    public boolean validateTransaction(Transaction transaction) {
        double amount = transaction.getAmount();
        String merchant = transaction.getMerchant();
        // Implement fraud validation logic here
        return true;
    }
}
