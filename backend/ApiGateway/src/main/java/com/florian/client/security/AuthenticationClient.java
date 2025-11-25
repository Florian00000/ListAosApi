package com.florian.client.security;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/auth")
@RegisterRestClient(configKey = "security-service")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface AuthenticationClient {

    @POST
    @Path("/register")
    Response registerUser(String jsonBody);

    @POST
    @Path("/login")
    Response loginUser(String jsonBody);
}
