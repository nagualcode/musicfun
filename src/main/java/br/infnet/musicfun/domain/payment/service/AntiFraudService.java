package br.infnet.musicfun.domain.payment.service;

import br.infnet.musicfun.domain.payment.model.Transaction;
import org.springframework.stereotype.Service;

@Service
public class AntiFraudService {

    public boolean validateTransaction(Transaction transaction) {
        double amount = transaction.getAmount();
        String merchant = transaction.getMerchant();

        // Implement fraud validation logic here
        // Example: Flag transactions over a certain amount
        if (amount > 10000) {
            return false; // Transaction is flagged as potentially fraudulent
        }

        // Example: Flag transactions from specific merchants
        if ("suspicious_merchant".equalsIgnoreCase(merchant)) {
            return false; // Transaction is flagged as potentially fraudulent
        }

        return true; // Transaction is considered valid
    }
}
