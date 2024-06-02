package com.Tutienda.service;



import com.Tutienda.entity.users.Address;
import com.Tutienda.entity.users.User;

import java.util.Optional;

public interface IUserService {
    User createUser(User user);
    Optional<User> getUserByUsername(String name);
    User updateUser(User user);
    void deleteUser(User usuario);
    Optional<User> getUserById(Long id);
    void updateAddressUser(Address address, Long userId);
}
