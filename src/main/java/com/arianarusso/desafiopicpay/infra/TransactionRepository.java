package com.arianarusso.desafiopicpay.infra;

import com.arianarusso.desafiopicpay.core.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository <Transaction, Long>{

}
