package com.example.doanjava.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.doanjava.entity.CustomUserDetail;
import com.example.doanjava.entity.User;
import com.example.doanjava.repository.IUserRepository;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user=userRepository.findByUserName(username);
        if (user == null)
            throw new UsernameNotFoundException("User not found");
        return new CustomUserDetail(user,userRepository);
    }
    public Optional<User> findByUsername(String username) throws
            UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
