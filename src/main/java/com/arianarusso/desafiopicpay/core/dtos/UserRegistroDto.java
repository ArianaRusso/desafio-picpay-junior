package com.arianarusso.desafiopicpay.core.dtos;

import com.arianarusso.desafiopicpay.core.entities.UserType;
import jakarta.persistence.Column;

import java.math.BigDecimal;

public record UserRegistroDto(
        String firstName,
        String lastName,
        String document,
        String email,
        String password,
        BigDecimal balance,
        UserType userType
) {
}
