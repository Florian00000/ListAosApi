package com.florian.resource.battlescroll;

import com.florian.client.battlescroll.MagicPrayerClient;
import com.florian.filter.RequiresRoles;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/api/spells-&-prayers")
@Produces(MediaType.APPLICATION_JSON)
public class MagicPrayerResource {

    @Inject @RestClient
    MagicPrayerClient magicPrayerClient;

    @GET
    @Path("/{id}")
    public Response getMagicPrayer(@PathParam("id") String id){
        return magicPrayerClient.getMagicPrayer(id);
    }

    @GET
    public Response getAllMagicPrayers(){
        return magicPrayerClient.getAllMagicPrayers();
    }

    @POST
    @Path("/add")
    @RequiresRoles("ADMIN")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMagicPrayer(String jsonBody){
        return magicPrayerClient.addMagicPrayers(jsonBody);
    }

    @PUT
    @Path("/update/{id}")
    @RequiresRoles("ADMIN")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMagicPrayer(@PathParam("id") String id, String jsonBody){
        return magicPrayerClient.updateMagicPrayer(id, jsonBody);
    }

    @DELETE
    @Path("/delete/{id}")
    @RequiresRoles("ADMIN")
    public Response deleteMagicPrayer(@PathParam("id") String id){
        return magicPrayerClient.deleteMagicPRayer(id);
    }
}
