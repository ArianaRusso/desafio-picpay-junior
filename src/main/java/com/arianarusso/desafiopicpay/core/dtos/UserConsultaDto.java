package com.arianarusso.desafiopicpay.core.dtos;

import com.arianarusso.desafiopicpay.core.entities.UserType;

import java.math.BigDecimal;

public record UserConsultaDto(
        Long id,
        String firstName,
        String lastName,
        String document,
        String email,
        BigDecimal balance,
        UserType userType
) {
}
