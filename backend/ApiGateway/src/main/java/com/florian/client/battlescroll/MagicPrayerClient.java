package com.florian.client.battlescroll;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


@Path("/api/spells-&-prayers")
@RegisterRestClient(configKey = "battlescroll-service")
@Produces(MediaType.APPLICATION_JSON)
public interface MagicPrayerClient {

    @GET
    @Path("/{id}")
    Response getMagicPrayer(@PathParam("id") String id);

    @GET
    Response getAllMagicPrayers();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    Response addMagicPrayers(String jsonBody);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update/{id}")
    Response updateMagicPrayer(@PathParam("id") String id, String jsonBody);

    @DELETE
    @Path("/delete/{id}")
    Response deleteMagicPRayer(@PathParam("id") String id);
}
