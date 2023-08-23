package com.mercury.eventsercive.bean;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "venue")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Venue {

    @Id
    @SequenceGenerator(name = "venue_seq_gen", sequenceName = "venue_seq", allocationSize = 1)
    @GeneratedValue(generator="venue_seq_gen", strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String owner;

    @Column
    private String phone;

    @Column
    private int capacity;

    @Column
    private String image;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH, mappedBy = "venue")
    private List<Event> eventList;
}
