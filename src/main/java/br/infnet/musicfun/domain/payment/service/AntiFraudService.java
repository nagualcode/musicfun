package br.infnet.musicfun.domain.payment.service;

import br.infnet.musicfun.domain.payment.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AntiFraudService {

    private final List<Transaction> recentTransactions = new ArrayList<>();

    public boolean validateTransaction(Transaction transaction) {
        double amount = transaction.getAmount();
        String merchant = transaction.getMerchant();
        LocalDateTime now = LocalDateTime.now();

        // Remove transactions older than 2 minutes from the history
        recentTransactions.removeIf(t -> t.getTimestamp().isBefore(now.minusMinutes(2)));

        // Check for high frequency transactions
        long countLast2Minutes = recentTransactions.stream()
                .filter(t -> t.getUserId().equals(transaction.getUserId()))
                .count();

        if (countLast2Minutes >= 3) {
            return false; // More than 3 transactions in the last 2 minutes
        }

        // Check for duplicate transactions
        long duplicateCount = recentTransactions.stream()
                .filter(t -> t.getUserId().equals(transaction.getUserId()))
                .filter(t -> t.getAmount() == amount)
                .filter(t -> t.getMerchant().equalsIgnoreCase(merchant))
                .count();

        if (duplicateCount >= 2) {
            return false; // More than 2 similar transactions (same amount and merchant) in the last 2 minutes
        }

        // Implement existing fraud validation logic
        if (amount > 10000) {
            return false; // Transaction is flagged as potentially fraudulent
        }

        if ("suspicious_merchant".equalsIgnoreCase(merchant)) {
            return false; // Transaction is flagged as potentially fraudulent
        }

        // Add the transaction to the history if it passes all checks
        recentTransactions.add(transaction);

        return true; // Transaction is considered valid
    }
}
