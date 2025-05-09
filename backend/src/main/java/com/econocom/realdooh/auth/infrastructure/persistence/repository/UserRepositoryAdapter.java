package com.econocom.realdooh.auth.infrastructure.persistence.repository;

import com.econocom.realdooh.auth.domain.model.User;
import com.econocom.realdooh.auth.domain.port.outbound.persistence.UserRepository;
import com.econocom.realdooh.auth.domain.vo.user.Email;
import com.econocom.realdooh.auth.infrastructure.persistence.mapper.UserEntityMapper;
import com.econocom.realdooh.shared.domain.exception.EntityNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository repository;
    private final UserEntityMapper mapper;

    @Override
    public User findByEmail(Email email) {
        return repository
            .findByEmail(email.getValue())
            .map(mapper::toDomain)
            .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

}
