package br.infnet.musicfun.service;

import br.infnet.musicfun.domain.payment.model.Transaction;
import br.infnet.musicfun.domain.payment.service.AntiFraudService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AntiFraudServiceTest {

    private AntiFraudService antiFraudService;

    @BeforeEach
    public void setUp() {
        antiFraudService = new AntiFraudService();
    }

    @Test
    public void testHighFrequencyTransactions() {
        Transaction transaction1 = createTransaction(1L, 100, "merchant1");
        Transaction transaction2 = createTransaction(1L, 200, "merchant2");
        Transaction transaction3 = createTransaction(1L, 300, "merchant3");
        Transaction transaction4 = createTransaction(1L, 400, "merchant4");

        assertTrue(antiFraudService.validateTransaction(transaction1));
        assertTrue(antiFraudService.validateTransaction(transaction2));
        assertTrue(antiFraudService.validateTransaction(transaction3));
        assertFalse(antiFraudService.validateTransaction(transaction4)); // Should be rejected
    }

    @Test
    public void testDuplicateTransactions() {
        Transaction transaction1 = createTransaction(1L, 100, "merchant1");
        Transaction transaction2 = createTransaction(1L, 100, "merchant1");
        Transaction transaction3 = createTransaction(1L, 100, "merchant1");

        assertTrue(antiFraudService.validateTransaction(transaction1));
        assertTrue(antiFraudService.validateTransaction(transaction2));
        assertFalse(antiFraudService.validateTransaction(transaction3)); // Should be rejected
    }

    @Test
    public void testHighValueTransaction() {
        Transaction transaction = createTransaction(1L, 15000, "merchant1");
        assertFalse(antiFraudService.validateTransaction(transaction)); // Should be rejected
    }

    @Test
    public void testSuspiciousMerchantTransaction() {
        Transaction transaction = createTransaction(1L, 100, "suspicious_merchant");
        assertFalse(antiFraudService.validateTransaction(transaction)); // Should be rejected
    }

    private Transaction createTransaction(Long userId, double amount, String merchant) {
        Transaction transaction = new Transaction();
        transaction.setUserId(userId);
        transaction.setAmount(amount);
        transaction.setMerchant(merchant);
        transaction.setTimestamp(LocalDateTime.now());
        return transaction;
    }
}
