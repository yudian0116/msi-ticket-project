package com.mercury.eventsercive.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "event")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
public class Event {

    @Id
    @SequenceGenerator(name = "event_seq_gen", sequenceName = "event_seq", allocationSize = 1)
    @GeneratedValue(generator="event_seq_gen", strategy = GenerationType.AUTO)
    @Getter
    private int id;

    @Column
    @Getter
    private String name;

    @Column(name = "start_time")
    @Getter
    private ZonedDateTime startTime;

    @Column(name = "end_time")
    @Getter
    private ZonedDateTime endTime;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private Venue venue;

    @ManyToOne
    @Getter
    private Category category;

    @Column
    @Getter
    private String image;

    @Column
    @Getter
    private String description;

    @JsonIgnore
    public Venue getVenue() {
        return venue;
    }
}
