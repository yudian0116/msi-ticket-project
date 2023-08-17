package com.mercury.ordersercive.service;

import com.mercury.ordersercive.bean.Order;
import com.mercury.ordersercive.bean.OrderItem;
import com.mercury.ordersercive.dao.EditOrderRequestDao;
import com.mercury.ordersercive.dao.OrderDao;
import com.mercury.ordersercive.dao.OrderItemDao;
import com.mercury.ordersercive.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderItemDao orderItemDao;

    @Autowired
    private EditOrderRequestDao editOrderRequestDao;

    public Order getOrderById(Integer id) {
        return orderDao.findById(id).get();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Response save(Order order) {
        try {
            List<OrderItem> purchases = order.getPurchases();
            purchases.forEach((orderItem) -> {
                orderItem.setTicket_id(orderItem.getTicket_id());

                orderItem.setOrder(order);
            });

            //orderHistoryDao.save(order);
            orderDao.save(order);

            return new Response(true);
        } catch (Exception e) {
            return new Response(false);
        }
    }

    public void deleteOrderItem(List<OrderItem> purchases) {orderItemDao.deleteAll(purchases);}
}
