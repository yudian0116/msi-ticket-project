package com.mercury.cartservice.service;

import com.mercury.cartservice.bean.Cart;
import com.mercury.cartservice.bean.CartItem;
import com.mercury.cartservice.dao.CartDao;
import com.mercury.cartservice.dao.CartItemDao;
import dto.TicketResponse;
import http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private CartItemDao cartItemDao;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public void deleteCartItem(List<CartItem> purchases) {cartItemDao.deleteAll(purchases);}

    public Cart getCartByUserId(int uid) {
        return cartDao.findByUserId(uid);
    }

    public Response save(Cart cart) {
        try {
            List<CartItem> purchases = cart.getToBePurchased();

            List<Integer> ticketIds = purchases.stream().map(CartItem::getTicketId).toList();

            TicketResponse[] ticketResponseArray = webClientBuilder.build().get()
                    .uri("http://event-service/tickets/list",
                            uriBuilder -> uriBuilder.queryParam("ticketId", ticketIds).build())
                    .retrieve()
                    .bodyToMono(TicketResponse[].class)
                    .block();

            for (int i = 0; i < ticketResponseArray.length; i++) {
                purchases.get(i).setPrice(ticketResponseArray[i].getPrice());
            }

            purchases.forEach((cartItem) -> {
                cartItem.setSubtotal(cartItem.getPrice() * cartItem.getQuantity());
                cartItem.setCart(cart);
            });

            cart.setTotal(purchases.stream().mapToDouble(CartItem::getSubtotal).sum());
            cartDao.save(cart);
            return new Response(true);
        } catch (Exception e) {
            return new Response(false);
        }
    }

    public void clearCart(int uid) {
        Cart cart = cartDao.findByUserId(uid);
        cartItemDao.deleteAll(cart.getToBePurchased());
        cart.setTotal(0);
        cartDao.save(cart);
    }

    // TODO: send request to order service to save order, then clear cart
    public void checkOut(int uid) {

    }

}
