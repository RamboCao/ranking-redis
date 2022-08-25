package com.rank.service;

import com.rank.config.FeignClientConfig;
import com.rank.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Path("/user")
@RequestMapping("user/feign")
@FeignClient(name = "ranking-redis", url = "localhost:9091", configuration = FeignClientConfig.class)
public interface IUserService {

    @GET
    @Path("/id/{id}")
    @RequestMapping(value = "user/id/{id}", method = RequestMethod.GET)
    User findById(@RequestParam("id") Long id);
}
