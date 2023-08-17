package com.mercury.ordersercive.dao;

import com.mercury.ordersercive.bean.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemDao extends JpaRepository<OrderItem, Integer> {
}
