package com.rank.service;

import com.rank.common.result.Result;

/**
 * @author Caolp
 */
public interface RankingService {

    /**
     * 获取 gameId 下，topN 的情况
     * @param gameId
     * @param topN
     * @param offset
     * @return
     */
    Result gameRankingTopN(Long gameId, Long topN, Long offset);
}
