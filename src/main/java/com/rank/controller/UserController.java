package com.rank.controller;

import com.rank.dao.UserRepository;
import com.rank.dto.User;
import com.rank.receiver.UserReceiver;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * @author Caolp
 */
@RestController
@RequestMapping("api/user-info")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @ResponseBody
    @RequestMapping(value = "/find-by-id", method = RequestMethod.GET)
    public User findIdByName(@RequestParam(value = "id",required = false) Long id){
        return userRepository.findByIdOrElseNull(id);
    }

    @ResponseBody
    @RequestMapping(value = "/find-by-name", method = RequestMethod.GET)
    public List<User> findIdByName(@RequestParam(value = "name",required = false) String name){
        return userRepository.findByNameLike(name);
    }

    @ResponseBody
    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public User addUser(@RequestBody UserReceiver userReceiver){
        User user = new User();
        BeanUtils.copyProperties(userReceiver, user);
        return userRepository.save(user);
    }

    @ResponseBody
    @RequestMapping(value = "/modify-user", method = RequestMethod.PUT)
    public User modifyUser(@RequestBody UserReceiver userReceiver){
        User user = new User();
        BeanUtils.copyProperties(userReceiver, user);
        user.setModifyInstant(Instant.now());
        return userRepository.save(user);
    }

}
