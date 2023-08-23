package com.mercury.usersercive.dao;

import com.mercury.usersercive.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
}
