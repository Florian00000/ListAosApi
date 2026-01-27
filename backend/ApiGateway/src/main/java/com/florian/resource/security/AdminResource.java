package com.florian.resource.security;

import com.florian.client.security.AdminClient;
import com.florian.filter.RequiresRoles;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/api/admin")
@Consumes(MediaType.APPLICATION_JSON)
public class AdminResource {

    @Inject @RestClient
    AdminClient adminClient;

    @POST
    @Path("/create-role")
    @RequiresRoles("ADMIN")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRole(String jsonBody){
        return adminClient.createRole(jsonBody);
    }

    @DELETE
    @Path("/delete-role")
    @RequiresRoles("ADMIN")
    public Response deleteRole(String jsonBody){
        return adminClient.deleteRole(jsonBody);
    }

    @PATCH
    @Path("/add-role-to-user/{id}")
    @RequiresRoles("ADMIN")
    public Response addRoleToUser(String jsonBody, @PathParam("id") String id){
        return adminClient.addRoleToUser(jsonBody, id);
    }
}
