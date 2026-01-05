package com.florian.resource.battlescroll;

import com.florian.client.battlescroll.BattleAptitudeClient;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/api/units")
@Produces(MediaType.APPLICATION_JSON)
public class BattleAptitudeResource {

    @Inject @RestClient
    BattleAptitudeClient battleAptitudeClient;

    @GET
    @Path("/{unityId}")
    public Response getUnity (@PathParam("unityId") String unityId) {
        return battleAptitudeClient.getUnity(unityId);
    }

    @GET
    public Response getAllUnits (){
        return battleAptitudeClient.getAllUnits();
    }
}
