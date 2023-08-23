package com.mercury.ordersercive.dao;

import com.mercury.ordersercive.bean.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDao extends JpaRepository<Order, Integer> {
    public List<Order> findByUserId(int uid);
}
