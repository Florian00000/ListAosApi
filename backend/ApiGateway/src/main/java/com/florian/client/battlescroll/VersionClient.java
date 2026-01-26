package com.florian.client.battlescroll;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/version")
@RegisterRestClient(configKey = "battlescroll-service")
@Produces(MediaType.APPLICATION_JSON)
public interface VersionClient {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    Response addVersion(String jsonBody);

    @GET
    @Path("/{versionId}")
    Response getVersion(@PathParam("versionId") String versionId);
}
