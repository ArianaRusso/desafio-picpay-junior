package com.arianarusso.desafiopicpay.application.services;

import com.arianarusso.desafiopicpay.core.dtos.NotificationDto;
import com.arianarusso.desafiopicpay.core.entities.UserEntity;
import com.arianarusso.desafiopicpay.core.exceptions.NotFoundResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {

    @Autowired
    RestTemplate restTemplate;

    public void sendNotification (UserEntity user, String message){
        String email = user.getEmail();
        NotificationDto request = new NotificationDto(email, message);

//        ResponseEntity<String> response = restTemplate.postForEntity
//                ("http://o4d9z.mocklab.io/notify",request, String.class);
//
//        if(!(response.getStatusCode() == HttpStatus.OK)){
//            throw new NotFoundResourceException("Resource not found");
//        }
        System.out.println("Notificação enviada!");
    }
}
