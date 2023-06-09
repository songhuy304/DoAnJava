package com.example.doanjava.services;

import com.example.doanjava.entity.User;
import com.example.doanjava.entity.product;
import com.example.doanjava.repository.IProductRepository;
import com.example.doanjava.repository.IRoleRepository;
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
    @Autowired
    private IRoleRepository roleRepository;



    public void save(User user){
        userRepository.save(user);
        Long userId = userRepository.getUserIdByUsername(user.getUsername());
        Long roleId = roleRepository.getRoleIdByName("USER");
        if (roleId != 0 && userId != 0){
            userRepository.addRoleToUser(userId,roleId);
        }
    }
    public List<User> getAllmoi(Integer pageNo,
                                   Integer pageSize,
                                   String sortBy) {
        List<User> users = userRepository.findAllBooks(pageNo, pageSize, sortBy);

        return users;
    }



}
