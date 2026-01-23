package com.florian.client.battlescroll;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;

import java.io.InputStream;

@Path("/api/units")
@RegisterRestClient(configKey = "battlescroll-service")
@Produces(MediaType.APPLICATION_JSON)
public interface UnityClient {

    @GET
    @Path("/{unityId}")
    Response getUnity(@PathParam("unityId") String unityId);

    @GET
    Response getAllUnits();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    Response addUnity(String jsonBody);

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/add")
    Response addUnityMultipart(
            @RestForm("unity") @PartType(MediaType.APPLICATION_JSON) InputStream unityStream,
            @RestForm("image") @PartType(MediaType.APPLICATION_OCTET_STREAM) InputStream image
            );

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update/{unityId}")
    Response updateUnity(@PathParam("unityId") String unityId, String jsonBody);

    @PUT
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/update/{unityId}")
    Response updateUnityMultipart(@PathParam("unityId") String unityId,
                         @RestForm("unity") @PartType(MediaType.APPLICATION_JSON) InputStream unityStream,
                         @RestForm("image") @PartType(MediaType.APPLICATION_OCTET_STREAM) InputStream image
                         );

    @DELETE
    @Path("/delete/{unityId}")
    Response deleteUnity(@PathParam("unityId") String unityId);
}
