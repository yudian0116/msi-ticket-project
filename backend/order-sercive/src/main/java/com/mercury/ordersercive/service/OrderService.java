package com.mercury.ordersercive.service;

import com.mercury.ordersercive.bean.Order;
import com.mercury.ordersercive.bean.OrderItem;
import com.mercury.ordersercive.dao.EditOrderRequestDao;
import com.mercury.ordersercive.dao.OrderDao;
import com.mercury.ordersercive.dao.OrderItemDao;
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
    private EditOrderRequestDao editOrderRequestDao;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Order getOrderById(Integer id) {
        return orderDao.findById(id).get();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Response save(Order order) {
        try {
            List<OrderItem> purchases = order.getPurchases();
            List<Integer> ticketIds = purchases.stream().map(OrderItem::getTicket_id).toList();

            TicketResponse[] ticketResponseArray = webClientBuilder.build().get()
                    .uri("http://event-service/tickets/list",
                            uriBuilder -> uriBuilder.queryParam("ticketId", ticketIds).build())
                    .retrieve()
                    .bodyToMono(TicketResponse[].class)
                    .block();

            // TODO: change to check if stock - quantity >= 0
            // boolean allTicketsAvail = Arrays.stream(ticketResponseArray).allMatch(TicketResponse::isAvailable);

            // TODO: map subtotal to each orderItem

            if(true) {
                purchases.forEach((orderItem) -> {
                    orderItem.setTicket_id(orderItem.getTicket_id());
                    orderItem.setQuantity(orderItem.getQuantity());
                    orderItem.setPrice(orderItem.getPrice());
                    orderItem.setSubtotal(orderItem.getSubtotal());
                    orderItem.setOrder(order);
                });
                orderDao.save(order);
            } else {
                throw new IllegalArgumentException("Ticket not available!");
            }

            double total = purchases.stream().mapToDouble(OrderItem::getSubtotal).sum();

            order.setTotal(total);
            order.setDate_time(ZonedDateTime.now());
            order.setStatus("open");
            return new Response(true);
        } catch (Exception e) {
            return new Response(false);
        }
    }

    public void deleteOrderItem(List<OrderItem> purchases) {orderItemDao.deleteAll(purchases);}

    public List<Order> getOrderByUserId(int uid) {
        return orderDao.findByUserId(uid);
    }
}
