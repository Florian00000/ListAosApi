package com.florian.resource.battlescroll;

import com.florian.client.battlescroll.UnityClient;
import com.florian.filter.RequiresRoles;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/api/units")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UnityResource {

    @Inject @RestClient
    UnityClient unityClient;

    @GET
    @Path("/{unityId}")
    public Response getUnity (@PathParam("unityId") String unityId) {
        return unityClient.getUnity(unityId);
    }

    @GET
    public Response getAllUnits (){
        return unityClient.getAllUnits();
    }

    @POST
    @Path("/add")
    @RequiresRoles("ADMIN")
    public Response addUnity(String jsonBody){
        return unityClient.addUnity(jsonBody);
    }
}
