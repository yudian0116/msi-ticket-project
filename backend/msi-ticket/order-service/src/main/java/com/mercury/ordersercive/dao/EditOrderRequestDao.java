package com.mercury.ordersercive.dao;

import com.mercury.ordersercive.bean.EditOrderRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditOrderRequestDao extends JpaRepository<EditOrderRequest, Integer> {
}
