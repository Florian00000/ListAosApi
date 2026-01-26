package com.florian.resource.battlescroll;

import com.florian.client.battlescroll.KeywordClient;
import com.florian.filter.RequiresRoles;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/api/keywords")
@Produces(MediaType.APPLICATION_JSON)
public class KeywordResource {

    @Inject @RestClient
    KeywordClient keywordClient;

    @GET
    @Path("/{keywordId}")
    public Response getKeyword(@PathParam("keywordId") String keywordId){
        return keywordClient.getKeyword(keywordId);
    }

    @GET
    public Response getAllKeywords(){
        return keywordClient.getAllKeywords();
    }

    @POST
    @Path("/add")
    @RequiresRoles("ADMIN")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addKeyword(String jsonBody){
        return keywordClient.addKeyword(jsonBody);
    }

    @PUT
    @Path("/update/{keywordId}")
    @RequiresRoles("ADMIN")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateKeyword(@PathParam("keywordId") String keywordId, String jsonBody){
        return keywordClient.updateKeyword(keywordId, jsonBody);
    }

    @DELETE
    @Path("/delete/{keywordId}")
    @RequiresRoles("ADMIN")
    public Response deleteKeyword(@PathParam("keywordId") String keywordId){
        return keywordClient.deleteKeyword(keywordId);
    }
}
