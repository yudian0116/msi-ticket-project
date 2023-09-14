package com.mercury.cartservice.controller;

import com.mercury.cartservice.bean.Cart;
import com.mercury.cartservice.bean.CartItem;
import com.mercury.cartservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/{uid}")
    public Cart getByUserId(@PathVariable int uid) {return cartService.getCartByUserId(uid);}

    @PostMapping
    public void save(@RequestBody Cart cart) {cartService.save(cart);}

    @DeleteMapping
    public void delete(@RequestParam List<CartItem> items) {cartService.deleteCartItem(items);}
}
