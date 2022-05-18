package com.rank.service.impl;

import com.rank.common.result.ResponseCode;
import com.rank.common.result.Result;
import com.rank.dao.UserRepository;
import com.rank.dto.GameRanking;
import com.rank.dto.User;
import com.rank.result.RankingInfo;
import com.rank.service.RankingService;
import com.rank.utils.redis.RedisZsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

/**
 * @author Caolp
 */
@Service
@Slf4j
public class RankingServiceImpl implements RankingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RankingServiceImpl.class);

    @Autowired
    RedisZsetUtil<Long, Long> redisZsetUtil;

    @Autowired
    UserRepository userRepository;


    private Result getRankingInfo(Long gameId, Long topN, Long offset) {
        return gameRankingTopN(gameId, topN, offset);
    }


    @Override
    public Result gameRankingTopN(Long gameId, Long topN, Long offset) {
        RankingInfo rankingInfo = new RankingInfo();
        try {
        List<GameRanking> gameRankingList = new ArrayList<>();
        Set<Long> userIds = redisZsetUtil.rangeRanking(gameId, 0L, topN - 1);

        for (Long userId : userIds) {
            User user = userRepository.findByIdOrElseNull(userId);
            GameRanking gameRanking = new GameRanking(offset, gameId, user.getId(), user.getScore(), offset, Instant.now(), null);
            gameRankingList.add(gameRanking);
        }

            rankingInfo.setRankingInfo(gameRankingList);
        }catch (Exception e){
            LOGGER.error("gameRanking exception", e);
            return Result.ofFail(ResponseCode.SERVER_ERROR);
        }
        return Result.ofSuccess(rankingInfo);
    }

    private GameRanking getSelfRankingInfo(Long gameId, Long userId) {
        Long ranking = redisZsetUtil.ranking(gameId, userId);
        User user = userRepository.findByIdOrElseNull(userId);
        return GameRanking.builder()
                .gameId(gameId)
                .userId(userId)
                .ranking(ranking)
                .score(user.getScore())
                .createInstant(Instant.now())
                .build();
    }
}
