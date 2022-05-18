package com.rank.receiver;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

/**
 * @author Caolp
 */
@Data
public class UserReceiver implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Double score;

    private Instant createInstant;

    private Long createId;

    private Long modifyId;

    private Instant modifyInstant;

}
