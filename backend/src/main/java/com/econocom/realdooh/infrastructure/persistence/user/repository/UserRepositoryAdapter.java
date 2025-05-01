package com.econocom.realdooh.infrastructure.persistence.user.repository;

import com.econocom.realdooh.domain.User;
import com.econocom.realdooh.domain.port.outbound.persistence.UserRepository;
import com.econocom.realdooh.domain.vo.Email;

import com.econocom.realdooh.infrastructure.persistence.user.UserEntity;
import com.econocom.realdooh.infrastructure.persistence.user.UserEntityMapper;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository repository;
    private final UserEntityMapper mapper;

    @Override
    public Optional<User> findByEmail(Email email) {
        Optional<UserEntity> entity = repository.findByEmail(email.getValue());

        if (entity.isPresent()) {
            return Optional.of(mapper.toDomain(entity.get()));
        }

        return Optional.empty();
    }

}
