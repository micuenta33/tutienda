package com.Tutienda.service;



import com.Tutienda.entity.usuarios.User;

import java.util.Optional;

public interface IUserService {
    User createUser(User usuario) throws Exception;
    Optional<User> getUserByUsername(String name);
    void deleteUser(User usuario);
}
