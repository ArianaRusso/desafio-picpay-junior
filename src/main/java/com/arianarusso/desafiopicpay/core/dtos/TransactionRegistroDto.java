package com.arianarusso.desafiopicpay.core.dtos;

import java.math.BigDecimal;


public record TransactionRegistroDto(
         BigDecimal value,
         Long senderId,
         Long receiverId
){
}
