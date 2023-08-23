package com.mercury.ordersercive.controller;

import com.mercury.ordersercive.bean.Order;
import com.mercury.ordersercive.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public Order getById(@PathVariable int id) {return orderService.getOrderById(id);}

    @GetMapping("/user/{uid}")
    public List<Order> getByUserId(@PathVariable int uid) {return orderService.getOrderByUserId(uid);}
}