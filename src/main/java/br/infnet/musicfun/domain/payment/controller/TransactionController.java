package br.infnet.musicfun.domain.payment.controller;

import br.infnet.musicfun.domain.payment.dto.TransactionDTO;
import br.infnet.musicfun.domain.payment.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        List<TransactionDTO> transactions = transactionService.findAll();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable Long id) {
        TransactionDTO transaction = transactionService.findById(id);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        TransactionDTO createdTransaction = transactionService.save(transactionDTO);
        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionDTO> updateTransaction(@PathVariable Long id, @RequestBody TransactionDTO transactionDTO) {
        TransactionDTO updatedTransaction = transactionService.update(id, transactionDTO);
        return new ResponseEntity<>(updatedTransaction, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
