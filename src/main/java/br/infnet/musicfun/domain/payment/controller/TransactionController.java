package br.infnet.musicfun.domain.payment.controller;

import br.infnet.musicfun.domain.payment.dto.TransactionDTO;
import br.infnet.musicfun.domain.payment.model.Transaction;
import br.infnet.musicfun.domain.payment.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<TransactionDTO> getAllTransactions() {
        return transactionService.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable Long id) {
        return transactionService.findById(id)
            .map(this::convertToDTO)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        Transaction transaction = convertToEntity(transactionDTO);
        Transaction savedTransaction = transactionService.save(transaction);
        return ResponseEntity.ok(convertToDTO(savedTransaction));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private TransactionDTO convertToDTO(Transaction transaction) {
        return TransactionDTO.builder()
            .id(transaction.getId())
            .amount(transaction.getAmount())
            .transactionDate(transaction.getTransactionDate())
            .status(transaction.getStatus())
            .merchant(transaction.getMerchant())
            .build();
    }

    private Transaction convertToEntity(TransactionDTO transactionDTO) {
        return Transaction.builder()
            .amount(transactionDTO.getAmount())
            .transactionDate(transactionDTO.getTransactionDate())
            .status(transactionDTO.getStatus())
            .merchant(transactionDTO.getMerchant())
            .build();
    }
}
