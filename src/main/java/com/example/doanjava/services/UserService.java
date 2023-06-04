package com.example.doanjava.services;

import com.example.doanjava.entity.User;
import com.example.doanjava.repository.IProductRepository;
import com.example.doanjava.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UserService {
    @Autowired
    private IUserRepository userRepository;
    public List<User> getAlluser(){

       return userRepository.findAll();
    }



}
