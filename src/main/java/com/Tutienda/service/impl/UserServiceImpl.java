package com.Tutienda.service.impl;


import com.Tutienda.entity.users.Address;
import com.Tutienda.entity.users.Role;
import com.Tutienda.entity.users.RoleEnum;
import com.Tutienda.entity.users.User;
import com.Tutienda.repository.IAddressRepository;
import com.Tutienda.repository.IUserRepository;
import com.Tutienda.service.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final IAddressRepository addressRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(IAddressRepository addressRepository, IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(User user)  {
        Role role1 = new Role(RoleEnum.ADMIN);
        Role role2 = new Role(RoleEnum.USER);
        role1.setUser(user);
        role2.setUser(user);
        user.setRoles(new HashSet<Role>(){{add(role1);}{add(role2);}});
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public User updateUser(User user) {
        Optional<User> userOptional = userRepository.findById(user.getId());
        if(userOptional.isPresent()){
            User userUpdated = userOptional.get();
            userUpdated.setFix(user.getFix());
            userUpdated.setMobile(user.getMobile());
            userUpdated.setEmail(user.getEmail());
            userUpdated.setUsername(user.getUsername());
            return userRepository.save(userUpdated);
        }
        return null;
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void updateAddressUser(Address address, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            User userUpdated = userOptional.get();
            if (userUpdated.getAddress() != null) {
                Optional<Address> addressOptional = addressRepository.findById(userUpdated.getAddress().getId());
                if(addressOptional.isPresent()){
                    Address addressUpdated = getAddress(address, addressOptional);
                    userUpdated.setAddress(addressUpdated);
                    userRepository.save(userUpdated);
                }
            }else {
                userUpdated.setAddress(address);
                userRepository.save(userUpdated);
            }

        }
    }
    private static Address getAddress(Address address, Optional<Address> addressOptional) {
        Address  addressUpdated = addressOptional.get();
        addressUpdated.setCountry(address.getCountry());
        addressUpdated.setCommunity(address.getCommunity());
        addressUpdated.setProvince(address.getProvince());
        addressUpdated.setCity(address.getCity());
        addressUpdated.setZipCode(address.getZipCode());
        addressUpdated.setStreet(address.getStreet());
        addressUpdated.setNumber(address.getNumber());
        addressUpdated.setFloor(address.getFloor());
        addressUpdated.setDoor(address.getDoor());
        return addressUpdated;
    }
}
