package com.mercury.ordersercive.bean;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Order {

    @Id
    @SequenceGenerator(name = "order_seq_gen", sequenceName = "order_seq", allocationSize = 1)
    @GeneratedValue(generator="order_seq_gen", strategy = GenerationType.AUTO)
    private int id;

    @Column
    private int user_id;

    @Column
    private double total;

    @Column
    private ZonedDateTime date_time;

    @Column
    private String status;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderItem> purchases;
}
