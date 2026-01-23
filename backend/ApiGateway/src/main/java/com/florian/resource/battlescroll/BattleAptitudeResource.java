package com.florian.resource.battlescroll;

import com.florian.client.battlescroll.BattleAptitudeClient;
import com.florian.filter.RequiresRoles;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/api/battle-aptitudes")
@Produces(MediaType.APPLICATION_JSON)
public class BattleAptitudeResource {

    @Inject @RestClient
    BattleAptitudeClient battleAptitudeClient;

    @GET
    @Path("/{id}")
    public Response getBattleAptitude(@PathParam("id") String id){
        return battleAptitudeClient.getBattleAptitude(id);
    }

    @GET
    public Response getAllBattleAptitudes(){
        return battleAptitudeClient.getAllBattleAptitudes();
    }

    @GET
    @Path("/find-by-charter-name/{charterName}")
    public Response getAllBattleAptitudesByCharterName(@PathParam("charterName") String charterName){
        return battleAptitudeClient.getAllBattleAptitudesByCharterName(charterName);
    }

    @POST
    @Path("/add")
    @RequiresRoles("ADMIN")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBattleAptitude(String jsonBody){
        return battleAptitudeClient.addBattleAptitude(jsonBody);
    }

    @PUT
    @Path("/update/{id}")
    @RequiresRoles("ADMIN")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBattleAptitude(@PathParam("id") String id, String jsonBody){
        return battleAptitudeClient.updateBattleAptitude(id, jsonBody);
    }

    @DELETE
    @Path("/delete/{id}")
    @RequiresRoles("ADMIN")
    public Response deleteBattleAptitude(@PathParam("id") String id){
        return battleAptitudeClient.deleteBattleAptitude(id);
    }

    @PATCH
    @Path("/attach/{idBa}/charter/{idCharter}")
    @RequiresRoles("ADMIN")
    public Response attachBattleAptitudeToCharter(@PathParam("idBa") String idBa,
                                                  @PathParam("idCharter") String idCharter){
        return battleAptitudeClient.attachBattleAptitudeToCharter(idBa, idCharter);
    }

    @PATCH
    @Path("/detach-to-charter/{idBa}")
    @RequiresRoles("ADMIN")
    public Response detachBattleAptitudeToCharter(@PathParam("idBa") String idBa){
        return battleAptitudeClient.detachBattleAptitudeToCharter(idBa);
    }
}
