package com.econocom.realdooh.auth.infrastructure.persistence.repository;

import com.econocom.realdooh.auth.domain.model.User;
import com.econocom.realdooh.auth.domain.port.outbound.persistence.UserRepository;
import com.econocom.realdooh.auth.domain.vo.user.Email;
import com.econocom.realdooh.auth.infrastructure.persistence.mapper.UserEntityMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository repository;
    private final UserEntityMapper mapper;

    @Override
    public Optional<User> findByEmail(Email email) {
        return repository.findByEmail(email.getValue()).map(mapper::toDomain);
    }
}
