package com.florian.client.security;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/admin")
@RegisterRestClient(configKey = "security-service")
@Consumes(MediaType.APPLICATION_JSON)
public interface AdminClient {

    @POST
    @Path("/create-role")
    @Produces(MediaType.APPLICATION_JSON)
    Response createRole(String jsonBody);

    @DELETE
    @Path("/create-role")
    Response deleteRole(String jsonBody);

    @PATCH
    @Path("/add-role-to-user/{id}")
    Response addRoleToUser(String jsonBody, @PathParam("id") String id);
}
