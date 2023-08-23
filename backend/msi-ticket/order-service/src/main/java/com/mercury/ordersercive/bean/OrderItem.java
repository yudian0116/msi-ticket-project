package com.mercury.ordersercive.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_item")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
public class OrderItem {

    @Id
    @SequenceGenerator(name = "order_item_seq_gen", sequenceName = "order_item_seq", allocationSize = 1)
    @GeneratedValue(generator="order_item_seq_gen", strategy = GenerationType.AUTO)
    @Getter
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private Order order;

    @Column
    @Getter
    private int ticket_id;

    @Column
    @Getter
    private int quantity;

    @Column
    @Getter
    private float subtotal;

    @JsonIgnore
    public Order getOrder() {
        return order;
    }
}
