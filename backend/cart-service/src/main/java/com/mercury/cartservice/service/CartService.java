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

    public Cart getCartById(Integer id) {return cartDao.findById(id).get();}

    public void deleteCartItem(List<CartItem> purchases) {cartItemDao.deleteAll(purchases);}

    public Cart getCartByUserId(int uid) {
        return cartDao.findByUserId(uid);
    }

    public Response save(Cart cart) {
        try {
            List<CartItem> purchases = cart.getToBePurchased();

            List<Integer> ticketIds = purchases.stream().map(CartItem::getTicket_id).toList();

            TicketResponse[] ticketResponseArray = webClientBuilder.build().get()
                    .uri("http://event-service/tickets/list",
                            uriBuilder -> uriBuilder.queryParam("ticketId", ticketIds).build())
                    .retrieve()
                    .bodyToMono(TicketResponse[].class)
                    .block();

            // TODO: map subtotal to each cartItem

            purchases.forEach((cartItem) -> {
                cartItem.setTicket_id(cartItem.getTicket_id());
                cartItem.setQuantity(cartItem.getQuantity());
                cartItem.setCart(cart);
            });
            cartDao.save(cart);
            return new Response(true);
        } catch (Exception e) {
            return new Response(false);
        }
    }
}
