package com.mercury.cartservice.dao;

import com.mercury.cartservice.bean.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartDao extends JpaRepository<Cart, Integer> {
    public Cart findByUserId(int uid);
}
