package com.arianarusso.desafiopicpay.application.controllers;

import com.arianarusso.desafiopicpay.application.services.UserService;
import com.arianarusso.desafiopicpay.core.UserMapper;
import com.arianarusso.desafiopicpay.core.dtos.TransactionConsultaDto;
import com.arianarusso.desafiopicpay.core.dtos.UserConsultaDto;
import com.arianarusso.desafiopicpay.core.dtos.UserRegistroDto;
import com.arianarusso.desafiopicpay.core.entities.UserEntity;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<UserConsultaDto> createUser(@RequestBody UserRegistroDto dto){
        UserEntity user= service.salveUser(UserMapper.dtoToEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.entityToDto(user));
    }

    @GetMapping
    public ResponseEntity<Page<UserConsultaDto>> listAll(@PageableDefault Pageable pageable){
        return ResponseEntity.ok(service.listAll(pageable).map(UserMapper::entityToDto));

    }
}
