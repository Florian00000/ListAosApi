package com.florian.resource.battlescroll;

import com.florian.client.battlescroll.DomainClient;
import com.florian.filter.RequiresRoles;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/api/domains")
@Produces(MediaType.APPLICATION_JSON)
public class DomainResource {

    @Inject @RestClient
    DomainClient domainClient;

    @GET
    @Path("/{id}")
    public Response getDomain(@PathParam("id") String id){
        return domainClient.getDomain(id);
    }

    @GET
    public Response getAllDomains(){
        return domainClient.getAllDomains();
    }

    @GET
    @Path("/find-by-charter-name/{charterName}")
    public Response getAllDomainsByCharterName(@PathParam("charterName") String charterName){
        return domainClient.getAllDomainsByCharterName(charterName);
    }

    @POST
    @Path("/add")
    @RequiresRoles("ADMIN")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDomain(String jsonBody){
        return domainClient.addDomain(jsonBody);
    }

    @PUT
    @Path("/update/{id}")
    @RequiresRoles("ADMIN")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDomain(@PathParam("id") String id, String jsonBody){
        return domainClient.updateDomain(id, jsonBody);
    }

    @DELETE
    @Path("/delete/{id}")
    @RequiresRoles("ADMIN")
    public Response deleteDomain(@PathParam("id") String id){
        return domainClient.deleteDomaine(id);
    }
}
