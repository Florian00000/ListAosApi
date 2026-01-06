package com.florian.client.battlescroll;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/units")
@RegisterRestClient(configKey = "battlescroll-service")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface UnityClient {

    @GET
    @Path("/{unityId}")
    Response getUnity(@PathParam("unityId") String unityId);

    @GET
    Response getAllUnits();

    @POST
    @Path("/add")
    Response addUnity(String jsonBody);
}
