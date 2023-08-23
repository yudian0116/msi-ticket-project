package com.mercury.usersercive.service;

import com.mercury.usersercive.bean.Role;
import com.mercury.usersercive.bean.User;
import com.mercury.usersercive.dao.UserDao;
import com.mercury.usersercive.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUserById(Integer id) {return userDao.findById(id).get();}

    public Response register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(new Role(2));
        userDao.save(user);
        return new Response(true);
    }

    public Response deleteUser(int id) {
        if(userDao.findById(id).get() != null) {
            userDao.deleteById(id);
            return new Response(true);
        }else {
            return new Response(false, "User is not found!");
        }
    }






}
