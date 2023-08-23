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
    @SequenceGenerator(name = "cart_item_seq_gen", sequenceName = "cart_item_seq", allocationSize = 1)
    @GeneratedValue(generator="cart_item_seq_gen", strategy = GenerationType.AUTO)
    @Getter
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private Cart cart;

    @Column
    @Getter
    private int ticket_id;

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
