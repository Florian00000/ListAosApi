package com.florian.client.battlescroll;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/battle-aptitudes")
@RegisterRestClient(configKey = "battlescroll-service")
@Produces(MediaType.APPLICATION_JSON)
public interface BattleAptitudeClient {

    @GET
    @Path("/{id}")
    Response getBattleAptitude(@PathParam("id") String id);

    @GET
    Response getAllBattleAptitudes();

    @GET
    @Path("/find-by-charter-name/{charterName}")
    Response getAllBattleAptitudesByCharterName(@PathParam("charterName") String charterName);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    Response addBattleAptitude(String jsonBody);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update/{id}")
    Response updateBattleAptitude(@PathParam("id") String id, String jsonBody);

    @DELETE
    @Path("/delete/{id}")
    Response deleteBattleAptitude(@PathParam("id") String id);

    @PATCH
    @Path("/attach/{idBa}/charter/{idCharter}")
    Response attachBattleAptitudeToCharter(@PathParam("idBa") String idBa, @PathParam("idCharter") String idCharter);

    @PATCH
    @Path("/detach-to-charter/{idBa}")
    Response detachBattleAptitudeToCharter(@PathParam("idBa") String idBa);
}
