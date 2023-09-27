package com.arianarusso.desafiopicpay.core.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionConsultaDto(
        Long id,
        BigDecimal value,
        Long senderId,
        Long receiverId,
        LocalDateTime timesstamp
) {
}
