package com.arianarusso.desafiopicpay.application.services;

import com.arianarusso.desafiopicpay.core.entities.Transaction;
import com.arianarusso.desafiopicpay.core.exceptions.ErrorConflictException;
import com.arianarusso.desafiopicpay.core.entities.UserEntity;
import com.arianarusso.desafiopicpay.core.entities.UserType;
import com.arianarusso.desafiopicpay.core.exceptions.NotFoundResourceException;
import com.arianarusso.desafiopicpay.infra.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserEntity salveUser(UserEntity user){
//        if(repository.findUserEntityByDocument(user.getDocument()).isPresent()){
//            throw new ErrorConflictException("User already exists with this document");
//        }
        return repository.save(user);
    }

    public Page<UserEntity> listAll(Pageable pageacle){
        return repository.findAll(pageacle);
    }

    public Optional<UserEntity> findUser(String document){
        Optional<UserEntity> user= repository.findUserEntityByDocument(document);
        return user;
    }

    public void validateTransaction(UserEntity sender, BigDecimal valueTransaction){
        if(sender.getUserType().equals(UserType.MERCHANT)){
            throw new ErrorConflictException("Unauthorized transaction");
        }

        if(sender.getBalance().compareTo(valueTransaction) > 0){
            throw new ErrorConflictException("Insufficient funds");
        }
    }

    public UserEntity findUserById(Long id){
        return repository.findById(id).
                orElseThrow(() -> new NotFoundResourceException("Resource not found"));
    }

    public void updateBalance(Transaction transaction){
        UserEntity sender = transaction.getSender();
        UserEntity reciver = transaction.getReceiver();
        sender.setBalance(sender.getBalance().subtract(transaction.getAmount()));
        reciver.setBalance(reciver.getBalance().add(transaction.getAmount()));
        salveUser(sender);
        salveUser(reciver);
    }
}
