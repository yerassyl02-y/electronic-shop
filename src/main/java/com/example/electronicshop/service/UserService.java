package com.example.electronicshop.service;

import com.example.electronicshop.model.User;
import com.example.electronicshop.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public void updateUser(Long id, User user) {
        User userFromDb = userRepo.findById(id).get();
        userFromDb.getRoles().clear();
        System.out.println(userFromDb.toString());
        userFromDb.setUsername(user.getUsername());
        userFromDb.setRoles(user.getRoles());
        userRepo.save(userFromDb);
    }

}
