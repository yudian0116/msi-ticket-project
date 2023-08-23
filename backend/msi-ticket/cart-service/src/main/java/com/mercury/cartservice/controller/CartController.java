package com.mercury.cartservice.controller;

import com.mercury.cartservice.bean.Cart;
import com.mercury.cartservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/{uid}")
    public Cart getByUserId(@PathVariable int uid) {return cartService.getCartByUserId(uid);}
}
