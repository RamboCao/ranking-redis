package com.rank.result;

import com.rank.dto.GameRanking;

import java.util.List;

/**
 * @author Caolp
 */
public class RankingInfo {

    /**
     * 排行榜信息
     */
    private List<GameRanking> rankingInfo;

    /**
     * 个人信息
     */
    private GameRanking selfRankingInfo;

    public RankingInfo() {

    }

    public List<GameRanking> getRankingInfo() {
        return rankingInfo;
    }

    public void setRankingInfo(List<GameRanking> rankingInfo) {
        this.rankingInfo = rankingInfo;
    }

    public RankingInfo(GameRanking selfRankingInfo) {
        this.selfRankingInfo = selfRankingInfo;
    }

    public void setSelfRankingInfo(GameRanking selfRankingInfo) {
        this.selfRankingInfo = selfRankingInfo;
    }
}
