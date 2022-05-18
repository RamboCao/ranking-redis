package com.rank.dto;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * @author Caolp
 */
@Entity
@Data
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = -513467905363162309L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "score")
    private Double score;

    @Column(name = "create_id")
    private Long createId;

    @Column(name = "create_instant")
    private Instant createInstant;

    @Column(name = "modify_id")
    private Long modifyId;

    @Column(name = "modify_instant")
    private Instant modifyInstant;
}
