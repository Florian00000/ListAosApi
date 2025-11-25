package com.florian.exception;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {

    private static final Logger LOG = Logger.getLogger(WebApplicationExceptionMapper.class);

    //Permet d'avoir les erreurs personnalisées des microservices

    @Override
    public Response toResponse(WebApplicationException e) {
        Response response = e.getResponse();

        LOG.warnf("Erreur recue du microservice - Status: %d", response.getStatus());

        // Retourne directement la réponse du microservice
        return response;
    }
}