package com.Tutienda.repository;

import com.Tutienda.entity.users.User;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.Optional;


@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
