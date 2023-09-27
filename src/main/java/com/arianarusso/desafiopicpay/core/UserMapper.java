package com.arianarusso.desafiopicpay.core;

import com.arianarusso.desafiopicpay.core.dtos.UserConsultaDto;
import com.arianarusso.desafiopicpay.core.dtos.UserRegistroDto;
import com.arianarusso.desafiopicpay.core.entities.UserEntity;
import org.apache.catalina.User;

public class UserMapper {
    public static UserConsultaDto entityToDto(UserEntity user){
        return new UserConsultaDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getDocument(),
                user.getEmail(),
                user.getBalance(),
                user.getUserType());

    }

    public static UserEntity dtoToEntity(UserRegistroDto dto){
        return new UserEntity(
                null,
                dto.firstName(),
                dto.lastName(),
                dto.document(),
                dto.email(),
                dto.password(),
                dto.balance(),
                dto.userType());
    }
}
