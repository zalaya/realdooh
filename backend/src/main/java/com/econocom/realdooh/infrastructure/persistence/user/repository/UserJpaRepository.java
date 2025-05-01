package com.econocom.realdooh.infrastructure.persistence.user.repository;

import com.econocom.realdooh.infrastructure.persistence.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

}
