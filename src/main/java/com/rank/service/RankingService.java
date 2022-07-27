package com.rank.service;

import com.rank.common.result.Result;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author Caolp
 */
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Path("/rank")
public interface RankingService {

    /**
     * 获取 gameId 下，topN 的情况
     * @param gameId
     * @param topN
     * @param offset
     * @return
     */
    @GET
    @Path("/get-top-n/{gameId}/{topN}/{offset}")
    Result gameRankingTopN(@PathParam("gameId") Long gameId, @PathParam("topN") Long topN, @PathParam("offset") Long offset);
}
