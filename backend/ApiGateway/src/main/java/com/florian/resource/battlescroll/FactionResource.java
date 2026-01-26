package com.florian.resource.battlescroll;

import com.florian.client.battlescroll.FactionClient;
import com.florian.filter.RequiresRoles;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;

import java.io.InputStream;

@Path("/api/factions")
@Produces(MediaType.APPLICATION_JSON)
public class FactionResource {

    @Inject @RestClient
    FactionClient factionClient;

    @GET
    @Path("/{factionId}")
    public Response getFaction(@PathParam("factionId") String factionId){
        return factionClient.getFaction(factionId);
    }

    @GET
    public Response getAllFactions(){
        return factionClient.getAllFactions();
    }

    @POST
    @Path("/add")
    @RequiresRoles("ADMIN")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addFaction(String jsonBody){
        return factionClient.addFaction(jsonBody);
    }

    @POST
    @Path("/add")
    @RequiresRoles("ADMIN")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response addFactionMultipart(
            @RestForm("faction") @PartType(MediaType.APPLICATION_JSON) InputStream factionStream,
            @RestForm("image") @PartType(MediaType.APPLICATION_OCTET_STREAM) InputStream image){
        return  factionClient.addFactionMultipart(factionStream, image);
    }

    @PUT
    @Path("/update/{factionId}")
    @RequiresRoles("ADMIN")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateFaction(@PathParam("factionId") String factionId, String jsonBody){
        return factionClient.updateFaction(factionId, jsonBody);
    }

    @PUT
    @Path("/update/{factionId}")
    @RequiresRoles("ADMIN")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response updateFactionMultipart(
            @PathParam("factionId") String factionId,
            @RestForm("faction") @PartType(MediaType.APPLICATION_JSON) InputStream factionStream,
            @RestForm("image") @PartType(MediaType.APPLICATION_OCTET_STREAM) InputStream image){
        return factionClient.updateFactionMultipart(factionId, factionStream, image);
    }

    @DELETE
    @Path("/delete/{factionId}")
    @RequiresRoles("ADMIN")
    public Response deleteFaction(@PathParam("factionId") String factionId){
        return factionClient.deleteFaction(factionId);
    }
}
