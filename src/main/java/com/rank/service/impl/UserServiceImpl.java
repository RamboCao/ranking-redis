package com.rank.service.impl;

import com.rank.dao.UserRepository;
import com.rank.dto.User;
import com.rank.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User findById(Long id) {

        return userRepository.findById(id).orElseThrow(RuntimeException::new);


    }
}
