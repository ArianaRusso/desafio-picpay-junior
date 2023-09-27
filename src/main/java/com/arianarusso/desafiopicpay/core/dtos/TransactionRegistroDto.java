package com.arianarusso.desafiopicpay.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;


public record TransactionDto(
         BigDecimal value,
         Long senderId,
         Long receiverId
){
}
