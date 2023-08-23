package com.mercury.ordersercive.bean;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "edit_order_request")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EditOrderRequest {
    @Id
    @SequenceGenerator(name = "edit_order_request_seq_gen", sequenceName = "edit_order_request_seq", allocationSize = 1)
    @GeneratedValue(generator="edit_order_request_seq_gen", strategy = GenerationType.AUTO)
    private int id;

    @Column
    private int user_id;

    @Column
    private int order_id;

    @Column
    private int old_order_item_id;

    @Column
    private int new_order_item_id;

    @Column
    private String status;
}
