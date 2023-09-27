package com.arianarusso.desafiopicpay.application.services;

import com.arianarusso.desafiopicpay.core.exceptions.ErrorConflictException;
import com.arianarusso.desafiopicpay.core.UserEntity;
import com.arianarusso.desafiopicpay.core.UserType;
import com.arianarusso.desafiopicpay.core.exceptions.NotFoundResourceException;
import com.arianarusso.desafiopicpay.infra.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void salveUser(UserEntity user){
        if(repository.findUserEntityByDocument(user.getDocument()).isPresent()){
            throw new ErrorConflictException("User already exists with this document");
        }
        repository.save(user);
    }

    public Optional<UserEntity> findUser(String document){
        Optional<UserEntity> user= repository.findUserEntityByDocument(document);
        return user;
    }

    public void validateTransaction(UserEntity sender, BigDecimal valueTransaction){
        if(sender.getUserType().equals(UserType.MERCHANT)){
            throw new ErrorConflictException("Unauthorized transaction");
        }

        if(sender.getBalance().compareTo(valueTransaction) >0){
            throw new ErrorConflictException("Insufficient funds");
        }
    }

    public UserEntity findUserById(Long id){
        return repository.findById(id).
                orElseThrow(() -> new NotFoundResourceException("Resource not found"));
    }
}
