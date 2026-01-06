package com.florian.filter;

import com.florian.client.security.TokenValidationClient;
import com.florian.dto.TokenValidationResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Filtre pour l'API Gateway qui valide le token et les rôles
 * en appelant le microservice d'authentification Spring Security
 */
@Provider
@RequiresRoles
@ApplicationScoped
public class RoleBasedAuthFilter implements ContainerRequestFilter {

    private static final Logger LOG = Logger.getLogger(RoleBasedAuthFilter.class);

    @Inject
    @RestClient
    TokenValidationClient tokenValidationClient;

    @Context
    ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String bearerToken = requestContext.getHeaders().getFirst("Authorization");

        if (bearerToken == null || bearerToken.isBlank()) {
            LOG.warn("Requête sans token d'autorisation");
            throw unauthorized("Token d'autorisation manquant");
        }

        // Appel au microservice Spring Security pour valider le token
        TokenValidationResponse validationResponse;
        try {
            validationResponse = tokenValidationClient.validateToken(bearerToken);
        } catch (Exception e) {
            LOG.error("Erreur lors de la validation du token", e);
            throw unauthorized("Impossible de valider le token");
        }

        if (!validationResponse.isValid()) {
            LOG.warn("Token invalide: " + validationResponse.getMessage());
            throw unauthorized(validationResponse.getMessage() != null ?
                    validationResponse.getMessage() : "Token invalide");
        }

        // Récupération de l'annotation @RequiresRoles
        Method method = resourceInfo.getResourceMethod();
        RequiresRoles rolesAnnotation = method.getAnnotation(RequiresRoles.class);

        if (rolesAnnotation == null) {
            rolesAnnotation = resourceInfo.getResourceClass().getAnnotation(RequiresRoles.class);
        }

        // Vérification des rôles si l'annotation est présente
        if (rolesAnnotation != null && rolesAnnotation.value().length > 0) {
            Set<String> requiredRoles = new HashSet<>(Arrays.asList(rolesAnnotation.value()));
            List<String> userAuthorities = validationResponse.getAuthorities();

            if (userAuthorities == null || userAuthorities.isEmpty()) {
                LOG.warn("Utilisateur sans authorities: " + validationResponse.getUsername());
                throw forbidden("Permissions insuffisantes");
            }

            // Conversion des authorities Spring Security (souvent avec préfixe ROLE_)
            Set<String> userRoles = extractRoles(userAuthorities);

            boolean hasAccess;
            if (rolesAnnotation.requireAll()) {
                hasAccess = userRoles.containsAll(requiredRoles);
            } else {
                hasAccess = requiredRoles.stream().anyMatch(userRoles::contains);
            }

            if (!hasAccess) {
                LOG.warn(String.format("Accès refusé pour %s. Rôles requis: %s, Rôles utilisateur: %s",
                        validationResponse.getUsername(), requiredRoles, userRoles));
                throw forbidden("Vous n'avez pas les permissions nécessaires pour accéder à cette ressource");
            }
        }

        // Transmission du token aux microservices en aval
        requestContext.getHeaders().putSingle("Authorization", bearerToken);

        // Optionnel: ajouter des headers supplémentaires pour les microservices
        requestContext.getHeaders().putSingle("X-User-Name", validationResponse.getUsername());

        LOG.debug("Accès autorisé pour l'utilisateur: " + validationResponse.getUsername());
    }

    /**
     * Extrait les rôles des authorities Spring Security
     * Gère le préfixe "ROLE_" si présent
     */
    private Set<String> extractRoles(List<String> authorities) {
        Set<String> roles = new HashSet<>();
        for (String authority : authorities) {
            // Spring Security ajoute souvent le préfixe ROLE_
            if (authority.startsWith("ROLE_")) {
                roles.add(authority.substring(5)); // Enlève "ROLE_"
            }
            roles.add(authority); // Ajoute aussi l'authority complète
        }
        return roles;
    }

    private NotAuthorizedException unauthorized(String message) {
        return new NotAuthorizedException(
                message,
                Response.status(Response.Status.UNAUTHORIZED)
                        .entity(String.format("{\"error\":\"%s\"}", message))
                        .build()
        );
    }

    private ForbiddenException forbidden(String message) {
        return new ForbiddenException(
                message,
                Response.status(Response.Status.FORBIDDEN)
                        .entity(String.format("{\"error\":\"%s\"}", message))
                        .build()
        );
    }
}