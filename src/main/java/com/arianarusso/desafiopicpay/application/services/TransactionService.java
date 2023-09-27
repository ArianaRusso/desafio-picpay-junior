package com.arianarusso.desafiopicpay.application.services;

import com.arianarusso.desafiopicpay.core.Transaction;
import com.arianarusso.desafiopicpay.core.UserEntity;
import com.arianarusso.desafiopicpay.infra.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    public void createTransation(Transaction transaction){
        repository.save(transaction);
    }
}
