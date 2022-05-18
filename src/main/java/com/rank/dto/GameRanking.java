package com.rank.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * @author Caolp
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameRanking {

    /**
     * id
     */
    private Long id;

    /**
     * 游戏id
     */
    private Long gameId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 分数
     */
    private Double score;

    /**
     * 排名
     */
    private Long ranking;

    /**
     * 创建时间
     */
    private Instant createInstant;

    /**
     * 修改时间
     */
    private Instant modifyInstant;


}
