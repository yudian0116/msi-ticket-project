package com.mercury.ordersercive.controller;

import com.mercury.ordersercive.bean.Order;
import com.mercury.ordersercive.service.OrderService;
import http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public Response save(@RequestBody Order order) {return orderService.save(order);}
}
