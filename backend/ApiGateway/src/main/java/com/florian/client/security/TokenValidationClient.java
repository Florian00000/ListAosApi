package com.florian.client.security;

import com.florian.dto.TokenValidationResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/auth/validate")
@RegisterRestClient(configKey = "security-service")
public interface TokenValidationClient {
    @GET
    TokenValidationResponse validateToken(@HeaderParam("Authorization") String bearerToken);
}
