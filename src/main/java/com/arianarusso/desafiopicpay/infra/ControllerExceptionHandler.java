package com.arianarusso.desafiopicpay.infra;

import com.arianarusso.desafiopicpay.core.dtos.ExceptionDto;
import com.arianarusso.desafiopicpay.core.exceptions.ErrorConflictException;
import com.arianarusso.desafiopicpay.core.exceptions.NotFoundResourceException;
import com.arianarusso.desafiopicpay.core.exceptions.TransactionNotAuthorized;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NotFoundResourceException.class)
    public ResponseEntity<ExceptionDto> notFoundResourceErro404(NotFoundResourceException ex){
        ExceptionDto dto = new ExceptionDto("Recurso não encontrado", "404");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
    }

    @ExceptionHandler(ErrorConflictException.class)
    public ResponseEntity<ExceptionDto> errorConflictErro409(ErrorConflictException ex){
        ExceptionDto dto = new ExceptionDto("Conflito", "409");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(dto);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> generalException(Exception ex){
        ExceptionDto dto = new ExceptionDto("Erro Interno", "500");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(dto);
    }

    @ExceptionHandler(TransactionNotAuthorized.class)
    public ResponseEntity<ExceptionDto> transactionNotAuthorizedErro401(TransactionNotAuthorized ex){
        ExceptionDto dto = new ExceptionDto("Transação não autorizada", "401");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(dto);
    }
}
