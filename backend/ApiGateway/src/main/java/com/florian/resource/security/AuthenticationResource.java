package com.florian.resource.security;

import com.florian.client.security.AuthenticationClient;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/api/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationResource {

    @Inject @RestClient
    AuthenticationClient authenticationClient;

    @POST
    @Path("/register")
    public Response registerUser(String jsonBody){
        return authenticationClient.registerUser(jsonBody);
    }

    @POST
    @Path("/login")
    public Response loginUser(String jsonBody){
        return authenticationClient.loginUser(jsonBody);
    }
}
