package com.florian.resource.battlescroll;

import com.florian.client.battlescroll.VersionClient;
import com.florian.filter.RequiresRoles;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/api/version")
@Produces(MediaType.APPLICATION_JSON)
public class VersionResource {

    @Inject @RestClient
    VersionClient versionClient;

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @RequiresRoles("ADMIN")
    public Response addVersion(String jsonBody){
        return  versionClient.addVersion(jsonBody);
    }

    @GET
    @Path("/{versionId}")
    public Response getVersion(@PathParam("versionId") String versionId){
        return versionClient.getVersion(versionId);
    }
}
