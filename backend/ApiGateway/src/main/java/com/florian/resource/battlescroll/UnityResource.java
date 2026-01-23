package com.florian.resource.battlescroll;

import com.florian.client.battlescroll.UnityClient;
import com.florian.filter.RequiresRoles;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;

import java.io.InputStream;

@Path("/api/units")
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
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUnity(String jsonBody){
        return unityClient.addUnity(jsonBody);
    }

    @POST
    @Path("/add")
    @RequiresRoles("ADMIN")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response addUnityMultipart(
            @RestForm("unity") @PartType(MediaType.APPLICATION_JSON) InputStream unityStream,
            @RestForm("image") @PartType(MediaType.APPLICATION_OCTET_STREAM) InputStream image){
        return unityClient.addUnityMultipart(unityStream, image);
    }

    @PUT
    @Path("/update/{unityId}")
    @RequiresRoles("ADMIN")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUnity(@PathParam("unityId") String unityId, String jsonBody){
        return unityClient.updateUnity(unityId, jsonBody);
    }

    @PUT
    @Path("/update/{unityId}")
    @RequiresRoles("ADMIN")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response updateUnityMultipart(@PathParam("unityId") String unityId,
                                @RestForm("unity") @PartType(MediaType.APPLICATION_JSON) InputStream unityStream,
                                @RestForm("image") @PartType(MediaType.APPLICATION_OCTET_STREAM) InputStream image
    ){
        return unityClient.updateUnityMultipart(unityId, unityStream, image);
    }

    @DELETE
    @Path("/delete/{unityId}")
    @RequiresRoles("ADMIN")
    public Response deleteUnity(@PathParam("unityId") String unityId){
        return unityClient.deleteUnity(unityId);
    }
}
