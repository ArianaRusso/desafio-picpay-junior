package com.arianarusso.desafiopicpay.application.services;

import com.arianarusso.desafiopicpay.core.dtos.TransactionConsultaDto;
import com.arianarusso.desafiopicpay.core.dtos.TransactionRegistroDto;
import com.arianarusso.desafiopicpay.core.entities.Transaction;
import com.arianarusso.desafiopicpay.core.entities.UserEntity;
import com.arianarusso.desafiopicpay.core.exceptions.NotFoundResourceException;
import com.arianarusso.desafiopicpay.core.exceptions.TransactionNotAuthorized;
import com.arianarusso.desafiopicpay.infra.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;

    public Transaction createTransation(TransactionRegistroDto dto){
        UserEntity sender = userService.findUserById(dto.senderId());
        UserEntity receiver = userService.findUserById(dto.receiverId());
        LocalDateTime time = LocalDateTime.now();
        userService.validateTransaction(sender, dto.value());

//        if(!authorizeTransaction(sender,dto.value())){
//            throw new TransactionNotAuthorized("Unauthorized Transaction");
//        }
        Transaction transaction = new Transaction(sender, receiver, dto.value(), time);
        userService.updateBalance(transaction);
        repository.save(transaction);
        notificationService.sendNotification(sender, "transaction completed successfully!");
        notificationService.sendNotification(receiver, "transaction received successfully!");
        return transaction;
    }

    public boolean authorizeTransaction(UserEntity sender, BigDecimal value){
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity
                ("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);
        if(authorizationResponse.getStatusCode() == HttpStatus.OK){
            String message = (String) authorizationResponse.getBody().get("message");
            return "Authorized".equalsIgnoreCase(message);
        } else return false;
    }

    public List<TransactionConsultaDto> listAllTransaction(){
        List<Transaction> list= repository.findAll();
        if(list.isEmpty()){
            throw new NotFoundResourceException("List is empty");
        }
        List<TransactionConsultaDto> listDtos = new ArrayList<>();
        for (Transaction transaction: list) {
            TransactionConsultaDto dto = new TransactionConsultaDto(
                    transaction.getId(),
                    transaction.getAmount(),
                    transaction.getSender().getId(),
                    transaction.getReceiver().getId(),
                    transaction.getTimesstamp());
            listDtos.add(dto);
        }
        return listDtos;
    }
}
