package com.ead.authuser.services;

import com.ead.authuser.models.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    Page<UserModel> findAll(Specification<UserModel> spec, Pageable pageable);

    Optional<UserModel> findById(UUID userId);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    void saveUser(UserModel userModel);

    void deleteUser(UserModel userModel);

    void updateUser(UserModel userModel);

    void updatePassword(UserModel userModel);
}
