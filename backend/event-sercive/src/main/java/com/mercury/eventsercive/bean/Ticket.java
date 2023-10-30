package com.mercury.eventsercive.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ticket")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Ticket {

    @Id
    @SequenceGenerator(name = "ticket_seq_gen", sequenceName = "ticket_seq", allocationSize = 1)
    @GeneratedValue(generator="ticket_seq_gen", strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JsonIgnore
    private Event event;

    @Column
    private double price;

    @Column
    private String type;
}
