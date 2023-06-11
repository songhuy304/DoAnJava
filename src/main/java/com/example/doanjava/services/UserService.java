package com.example.doanjava.services;

import com.example.doanjava.constants.Provider;
import com.example.doanjava.constants.Role;
import com.example.doanjava.entity.User;
import com.example.doanjava.entity.product;
import com.example.doanjava.repository.IProductRepository;
import com.example.doanjava.repository.IRoleRepository;
import com.example.doanjava.repository.IUserRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;


    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void someMethod(String username) {
        User user = userRepository.findByUserName(username);

    }
    public void save(User user){
        User existingUser = userRepository.findByUserName(user.getUsername());
        if(existingUser != null){
            return;

        }
        userRepository.save(user);
        Long userId = userRepository.getUserIdByUsername(user.getUsername());
        if (userId != null) {
            Long roleId = roleRepository.getRoleIdByName("USER");
            if (roleId != 0) {
                userRepository.addRoleToUser(userId.longValue(), roleId);
            }
        }



    }

    public void saveOauthUser(String email, @NotNull String username) {
        Optional<User> existingUser = userRepository.findByUsername(email);
        if (existingUser.isPresent()) {
            return ;
        }
        else {
            var user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setName(username);
            user.setPassword(new BCryptPasswordEncoder().encode(username));
            user.setProvider(Provider.GOOGLE.value);


//        user.getRoles().add(roleRepository.findRoleById(Role.USER.value));
            userRepository.save(user);
        }



    }
    public List<User> getAllmoi(Integer pageNo,
                                   Integer pageSize,
                                   String sortBy) {
        List<User> users = userRepository.findAllBooks(pageNo, pageSize, sortBy);

        return users;
    }



}
