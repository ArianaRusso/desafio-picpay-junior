package com.arianarusso.desafiopicpay.infra;

import com.arianarusso.desafiopicpay.core.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findUserEntityByDocument(String document);
    Page<UserEntity> findAll(Pageable pageable);
}
