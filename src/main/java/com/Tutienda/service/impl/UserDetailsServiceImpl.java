package com.Tutienda.service.impl;


import com.Tutienda.entity.users.Role;
import com.Tutienda.repository.IUserRepository;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional()
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserRepository userRepository;

    public UserDetailsServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      var opUser = userRepository.findByUsername(username);
      if(opUser.isEmpty()){
          throw new UsernameNotFoundException("el usuario no existe");
      }
        List<GrantedAuthority> roles=new ArrayList<>();
        for (Role role:opUser.get().getRoles()){
            roles.add(new SimpleGrantedAuthority(role.getRole().name()));
        }
        UserDetails userDetails =new User(opUser.get().getUsername(),opUser.get().getPassword(),roles);
        System.out.println(userDetails);
        return userDetails;
    }
}
