package com.mercury.cartservice.dao;

import com.mercury.cartservice.bean.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemDao extends JpaRepository<CartItem, Integer> {
}
