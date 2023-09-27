package com.arianarusso.desafiopicpay.application.controllers;

import com.arianarusso.desafiopicpay.application.services.TransactionService;
import com.arianarusso.desafiopicpay.application.services.UserService;
import com.arianarusso.desafiopicpay.core.dtos.TransactionConsultaDto;
import com.arianarusso.desafiopicpay.core.dtos.TransactionRegistroDto;
import com.arianarusso.desafiopicpay.core.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService service;


    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionRegistroDto dto){
        Transaction response = service.createTransation(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<TransactionConsultaDto>> listAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.listAllTransaction());
    }


}
