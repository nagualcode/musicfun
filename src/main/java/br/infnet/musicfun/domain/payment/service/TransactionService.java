package br.infnet.musicfun.domain.payment.service;

import br.infnet.musicfun.domain.payment.dto.TransactionDTO;
import br.infnet.musicfun.domain.payment.model.Transaction;
import br.infnet.musicfun.domain.payment.repository.TransactionRepository;
import br.infnet.musicfun.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AntiFraudService antiFraudService;

    public List<TransactionDTO> findAll() {
        return transactionRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public TransactionDTO findById(Long id) {
        return transactionRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));
    }

    public TransactionDTO save(TransactionDTO transactionDTO) {
        Transaction transaction = convertToEntity(transactionDTO);
        transaction.setTimestamp(LocalDateTime.now()); // Definir timestamp atual

        // Validar transação com AntiFraudService
        if (!antiFraudService.validateTransaction(transaction)) {
            throw new IllegalArgumentException("Transação suspeita de fraude.");
        }

        Transaction savedTransaction = transactionRepository.save(transaction);
        return convertToDTO(savedTransaction);
    }

    public TransactionDTO update(Long id, TransactionDTO transactionDTO) {
        if (!transactionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Transaction not found");
        }
        Transaction transaction = convertToEntity(transactionDTO);
        transaction.setId(id);
        transaction.setTimestamp(LocalDateTime.now()); // Atualizar timestamp

        // Validar transação com AntiFraudService
        if (!antiFraudService.validateTransaction(transaction)) {
            throw new IllegalArgumentException("Transação suspeita de fraude.");
        }

        Transaction updatedTransaction = transactionRepository.save(transaction);
        return convertToDTO(updatedTransaction);
    }

    public void delete(Long id) {
        if (!transactionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Transaction not found");
        }
        transactionRepository.deleteById(id);
    }

    private TransactionDTO convertToDTO(Transaction transaction) {
        return new TransactionDTO(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getSubscriptionId(),
                transaction.getMerchant(),
                transaction.getStatus(),
                transaction.getTimestamp()
        );
    }

    private Transaction convertToEntity(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setId(transactionDTO.getId());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setSubscriptionId(transactionDTO.getSubscriptionId());
        transaction.setMerchant(transactionDTO.getMerchant());
        transaction.setStatus(transactionDTO.getStatus());
        transaction.setTimestamp(transactionDTO.getTimestamp());
        return transaction;
    }
}
