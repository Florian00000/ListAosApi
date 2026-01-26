package com.florian.client.battlescroll;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;

import java.io.InputStream;

@Path("/api/factions")
@RegisterRestClient(configKey = "battlescroll-service")
@Produces(MediaType.APPLICATION_JSON)
public interface FactionClient {

    @GET
    @Path("/{factionId}")
    Response getFaction(@PathParam("factionId") String factionId);

    @GET
    Response getAllFactions();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    Response addFaction(String jsonBody);

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/add")
    Response addFactionMultipart(
            @RestForm("faction") @PartType(MediaType.APPLICATION_JSON) InputStream factionStream,
            @RestForm("image") @PartType(MediaType.APPLICATION_OCTET_STREAM) InputStream image
    );

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update/{factionId}")
    Response updateFaction(@PathParam("factionId") String factionId, String jsonBody);

    @PUT
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/update/{factionId}")
    Response updateFactionMultipart(@PathParam("factionId") String factionId,
                                    @RestForm("faction") @PartType(MediaType.APPLICATION_JSON) InputStream factionStream,
                                    @RestForm("image") @PartType(MediaType.APPLICATION_OCTET_STREAM) InputStream image
                                    );

    @DELETE
    @Path("/delete/{factionId}")
    Response deleteFaction(@PathParam("factionId") String factionId);
}
