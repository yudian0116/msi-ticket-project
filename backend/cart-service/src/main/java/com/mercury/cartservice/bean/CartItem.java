package com.mercury.cartservice.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart_item")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
public class CartItem {

    @Id
    @SequenceGenerator(name = "cart_item_seq_gen", sequenceName = "cart_item_seq", allocationSize = 15)
    @GeneratedValue(generator="cart_item_seq_gen", strategy = GenerationType.AUTO)
    @Getter
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Cart cart;

    @Column(name = "ticket_id")
    @Getter
    private int ticketId;

    @Column
    @Getter
    private double price;

    @Column
    @Getter
    private int quantity;

    @Column
    @Getter
    private double subtotal;

    @JsonIgnore
    public Cart getCart() {
        return cart;
    }
}
