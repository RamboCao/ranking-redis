package com.rank.controller;

import com.rank.annotation.AspectAnnotation;
import com.rank.common.result.Result;
import com.rank.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Caolp
 */
@RestController
@RequestMapping("api/user")
public class RankingController {

    @Autowired
    RankingService rankingService;

    @ResponseBody
    @AspectAnnotation
    @RequestMapping(value = "/top-n", method = RequestMethod.GET)
    public Result findRankingInfoTopN(@RequestParam(value = "n", required = true) Long n){
        Long gameId = 1000L;
        return rankingService.gameRankingTopN(gameId, n, 10L);
    }

}
