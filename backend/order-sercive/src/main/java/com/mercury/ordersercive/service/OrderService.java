package com.mercury.ordersercive.service;

import com.mercury.ordersercive.bean.Order;
import com.mercury.ordersercive.bean.OrderItem;
import com.mercury.ordersercive.dao.OrderDao;
import com.mercury.ordersercive.dao.OrderItemDao;
import dto.CheckoutItem;
import dto.InventoryResponse;
import dto.CheckoutRequest;
import dto.TicketResponse;
import http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderItemDao orderItemDao;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Order getOrderById(Integer id) {
        return orderDao.findById(id).get();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Response save(Order order) {
        try {
            List<OrderItem> purchases = order.getPurchases();
            List<Integer> ticketIds = purchases.stream().map(OrderItem::getTicketId).toList();

            InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
                    .uri("http://inventory-service/inventories/list",
                            uriBuilder -> uriBuilder.queryParam("ids", ticketIds).build())
                    .retrieve()
                    .bodyToMono(InventoryResponse[].class)
                    .block();

            for (int i = 0; i < inventoryResponseArray.length; i++) {
                if (inventoryResponseArray[i].getQuantity() - purchases.get(i).getQuantity() < 0) {
                    throw new IllegalArgumentException("Ticket not available!");
                } else {
                    inventoryResponseArray[i].setQuantity(inventoryResponseArray[i].getQuantity() - purchases.get(i).getQuantity());
                }
            }

            TicketResponse[] ticketResponseArray = webClientBuilder.build().get()
                    .uri("http://event-service/tickets/list",
                            uriBuilder -> uriBuilder.queryParam("ticketId", ticketIds).build())
                    .retrieve()
                    .bodyToMono(TicketResponse[].class)
                    .block();

            for (int i = 0; i < ticketResponseArray.length; i++) {
                purchases.get(i).setPrice(ticketResponseArray[i].getPrice());
            }

            purchases.forEach((orderItem) -> {
                orderItem.setSubtotal(orderItem.getPrice() * orderItem.getQuantity());
                orderItem.setOrder(order);
            });

            order.setTotal(purchases.stream().mapToDouble(OrderItem::getSubtotal).sum());
            order.setDate_time(ZonedDateTime.now());
            order.setStatus("open");
            orderDao.save(order);

            webClientBuilder.build().put()
                    .uri("http://inventory-service/inventories/multi")
                    .bodyValue(Arrays.asList(inventoryResponseArray))
                    .retrieve().bodyToMono(Response.class).block();

            return new Response(true);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Response saveFromCart(CheckoutRequest request) {
        try {
            List<CheckoutItem> purchases = request.getPurchases();
            List<Integer> ticketIds = purchases.stream().map(CheckoutItem::getTicketId).toList();
            InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
                    .uri("http://inventory-service/inventories/list",
                            uriBuilder -> uriBuilder.queryParam("ids", ticketIds).build())
                    .retrieve()
                    .bodyToMono(InventoryResponse[].class)
                    .block();

            for (int i = 0; i < inventoryResponseArray.length; i++) {
                if (inventoryResponseArray[i].getQuantity() - purchases.get(i).getQuantity() < 0) {
                    throw new IllegalArgumentException("Ticket not available!");
                } else {
                    inventoryResponseArray[i].setQuantity(inventoryResponseArray[i].getQuantity() - purchases.get(i).getQuantity());
                }
            }

            List<OrderItem> orderItems = purchases.stream().map((checkoutItem) -> {
                OrderItem orderItem = new OrderItem();
                orderItem.setTicketId(checkoutItem.getTicketId());
                orderItem.setQuantity(checkoutItem.getQuantity());
                orderItem.setPrice(checkoutItem.getPrice());
                orderItem.setSubtotal(checkoutItem.getSubtotal());
                return orderItem;
            }).toList();
            Order order = new Order();
            order.setUserId(request.getUserId());
            order.setTotal(request.getTotal());
            order.setPurchases(orderItems);
            order.setDate_time(ZonedDateTime.now());
            order.setStatus("open");
            orderDao.save(order);

            webClientBuilder.build().put()
                    .uri("http://inventory-service/inventories/multi")
                    .bodyValue(Arrays.asList(inventoryResponseArray))
                    .retrieve().bodyToMono(Response.class).block();

            return new Response(true);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    public void deleteOrderItem(List<OrderItem> purchases) {orderItemDao.deleteAll(purchases);}

    public List<Order> getOrderByUserId(int uid) {
        return orderDao.findByUserId(uid);
    }

    public void payOrder(int id) {
        Order order = orderDao.findById(id).get();
        order.setStatus("paid");
        orderDao.save(order);
    }

    public void completeOrder(int id) {
        Order order = orderDao.findById(id).get();
        order.setStatus("complete");
        orderDao.save(order);
    }
}
