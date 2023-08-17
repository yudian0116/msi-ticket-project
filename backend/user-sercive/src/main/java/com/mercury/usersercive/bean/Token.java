package com.mercury.usersercive.bean;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "tokens")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Token {
    @Id
    @SequenceGenerator(name = "token_seq_gen", sequenceName = "user_token_seq", allocationSize = 1)
    @GeneratedValue(generator = "token_seq_gen", strategy = GenerationType.AUTO)
    private int id;

    @Column
    private int user_id;

    @Column
    private String user_token;

    @Column
    private ZonedDateTime create_at;

    @Column
    private ZonedDateTime expire_at;
}
