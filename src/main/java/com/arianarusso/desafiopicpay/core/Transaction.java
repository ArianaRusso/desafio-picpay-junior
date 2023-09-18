package com.arianarusso.desafiopicpay.core;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "transactions")
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "sender-id")
    private UserEntity sender;

    @ManyToOne
    @JoinColumn(name = "receiver-id")
    private UserEntity receiver;

    private BigDecimal amount;

    private LocalDateTime timesstamp;



}
