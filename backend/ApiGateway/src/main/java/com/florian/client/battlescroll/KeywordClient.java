package com.florian.client.battlescroll;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/keywords")
@RegisterRestClient(configKey = "battlescroll-service")
@Produces(MediaType.APPLICATION_JSON)
public interface KeywordClient {

    @GET
    @Path("/{keywordId}")
    Response getKeyword(@PathParam("keywordId") String keywordId);

    @GET
    Response getAllKeywords();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    Response addKeyword(String jsonBody);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update/{keywordId}")
    Response updateKeyword(@PathParam("keywordId") String keywordId, String jsonBody);

    @DELETE
    @Path("/delete/{keywordId}")
    Response deleteKeyword(@PathParam("keywordId") String keywordId);
}
