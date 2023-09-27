package com.arianarusso.desafiopicpay.core.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transactions")
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender-id")
    private UserEntity sender;

    @ManyToOne
    @JoinColumn(name = "receiver-id")
    private UserEntity receiver;

    private BigDecimal amount;

    private LocalDateTime timesstamp;

    public Transaction(UserEntity sender, UserEntity receiver, BigDecimal value,  LocalDateTime time) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = value;
        this.timesstamp =  time;
    }
}
