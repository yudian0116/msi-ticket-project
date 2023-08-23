package com.mercury.cartservice.service;

import com.mercury.cartservice.bean.Cart;
import com.mercury.cartservice.bean.CartItem;
import com.mercury.cartservice.dao.CartDao;
import com.mercury.cartservice.dao.CartItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private CartItemDao cartItemDao;

    public Cart getCartById(Integer id) {return cartDao.findById(id).get();}

    public void deleteOrderItem(List<CartItem> purchases) {cartItemDao.deleteAll(purchases);}

    public Cart getCartByUserId(int uid) {
        return cartDao.findByUserId(uid);
    }
}
