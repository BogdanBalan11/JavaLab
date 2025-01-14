package com.unibuc.ex1curs11.controller;

import com.unibuc.ex1curs11.dto.TransactionDTO;
import com.unibuc.ex1curs11.service.TransactionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    @ApiOperation("Add a new transaction (income or expense)")
    public ResponseEntity<TransactionDTO> addTransaction(@RequestBody TransactionDTO transactionDTO) {
        TransactionDTO created = transactionService.addTransaction(transactionDTO);
        return ResponseEntity.ok(created);
    }
}