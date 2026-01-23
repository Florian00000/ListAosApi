package com.florian.client.battlescroll;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/domains")
@RegisterRestClient(configKey = "battlescroll-service")
@Produces(MediaType.APPLICATION_JSON)
public interface DomainClient {

    @GET
    @Path("/{id}")
    Response getDomain(@PathParam("id") String id);

    @GET
    Response getAllDomains();

    @GET
    @Path("/find-by-charter-name/{charterName}")
    Response getAllDomainsByCharterName(@PathParam("charterName") String charterName);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    Response addDomain(String jsonBody);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update/{id}")
    Response updateDomain(@PathParam("id") String id, String jsonBody);

    @DELETE
    @Path("/delete/{id}")
    Response deleteDomaine(@PathParam("id") String id);
}
