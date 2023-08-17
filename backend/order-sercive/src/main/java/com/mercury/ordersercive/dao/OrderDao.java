package com.mercury.ordersercive.dao;

import com.mercury.ordersercive.bean.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order, Integer> {
}
