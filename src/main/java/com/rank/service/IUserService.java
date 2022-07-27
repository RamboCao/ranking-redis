package com.rank.service;

import com.rank.dto.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Path("/user")
public interface IUserService {

    @GET
    @Path("/id/{id}")
    User findById(@PathParam("id") Long id);

    @GET
    @Path("test")
    void test();

}
