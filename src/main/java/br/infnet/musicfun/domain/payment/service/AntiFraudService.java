package br.infnet.musicfun.domain.payment.service;

import br.infnet.musicfun.domain.payment.model.Transaction;
import br.infnet.musicfun.domain.payment.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AntiFraudService {

    @Autowired
    private TransactionRepository transactionRepository;

    public boolean validateTransaction(Transaction transaction) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime twoMinutesAgo = now.minusMinutes(2);

        // Fetch recent transactions within the last 2 minutes for the same user
        List<Transaction> recentTransactions = transactionRepository.findByUserIdAndTransactionDateBetween(transaction.getUser().getId(), twoMinutesAgo, now);

        // Check for high-frequency small-interval transactions
        if (recentTransactions.size() >= 3) {
            return false; // More than 3 transactions in a 2-minute interval
        }

        // Check for duplicate transactions (same amount and merchant)
        long similarTransactionsCount = recentTransactions.stream()
                .filter(t -> t.getAmount().equals(transaction.getAmount()) && t.getMerchant().equals(transaction.getMerchant()))
                .count();

        if (similarTransactionsCount >= 2) {
            return false; // More than 2 similar transactions in a 2-minute interval
        }

        return true;
    }
}
