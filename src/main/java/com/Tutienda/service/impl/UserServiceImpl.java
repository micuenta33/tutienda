package com.Tutienda.service.impl;


import com.Tutienda.entity.users.User;
import com.Tutienda.repository.IUserRepository;
import com.Tutienda.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User usuario) throws Exception {
        return userRepository.save(usuario);
    }

    @Override
    public Optional<User> getUserByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public void deleteUser(User usuario) {
        userRepository.delete(usuario);
    }
}
