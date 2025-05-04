package com.econocom.realdooh.auth.infrastructure.persistence.repository;

import com.econocom.realdooh.auth.domain.model.User;
import com.econocom.realdooh.auth.domain.port.outbound.persistence.UserRepository;
import com.econocom.realdooh.auth.domain.vo.user.Email;
import com.econocom.realdooh.auth.domain.vo.user.HashedPassword;
import com.econocom.realdooh.auth.domain.vo.user.UserId;
import com.econocom.realdooh.auth.infrastructure.persistence.entity.UserEntity;
import com.econocom.realdooh.shared.domain.exception.EntityNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository repository;

    @Override
    public Optional<User> findByEmail(Email email) {
        String message = "User with email " + email.getValue() + " not found";
        UserEntity entity = repository.findByEmail(email.getValue()).orElseThrow(() -> new EntityNotFoundException(message));

        return Optional.of(new User(new UserId(entity.getId()), email, new HashedPassword(entity.getPassword())));
    }

}
