package com.mercury.eventsercive.bean;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Category {

    @Id
    @SequenceGenerator(name = "category_seq_gen", sequenceName = "category_seq", allocationSize = 1)
    @GeneratedValue(generator="category_seq_gen", strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;
}
