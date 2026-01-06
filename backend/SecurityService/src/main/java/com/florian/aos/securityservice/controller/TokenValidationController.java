package com.florian.aos.securityservice.controller;

import com.florian.aos.securityservice.config.jwt.JwtTokenProvider;
import com.florian.aos.securityservice.dto.apiGateway.TokenValidationResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class TokenValidationController {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    //Controller pour valider le token transmis par l'Api Gateway

    @GetMapping("/validate")
    public TokenValidationResponse validateToken(@RequestHeader("Authorization") String bearerToken) {

        if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
            return new TokenValidationResponse(false, null, null, "Format de token invalide");
        }

        String token = bearerToken.substring(7); // Enlève "Bearer "

        try {
            // Extraire le username du token
            String username = tokenProvider.getUsernameFromToken(token);

            if (username == null) {
                return new TokenValidationResponse(false, null, null, "Username introuvable dans le token");
            }

            // Charger les détails de l'utilisateur
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Valider le token
            if (tokenProvider.validateToken(token)) {
                // Extraire les authorities
                List<String> authorities = userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList());

                return new TokenValidationResponse(
                        true,
                        username,
                        authorities,
                        "Token valide"
                );
            } else {
                return new TokenValidationResponse(false, null, null, "Token invalide");
            }

        } catch (ExpiredJwtException e) {
            return new TokenValidationResponse(false, null, null, "Token expiré");
        } catch (MalformedJwtException e) {
            return new TokenValidationResponse(false, null, null, "Token mal formé");
        } catch (Exception e) {
            return new TokenValidationResponse(false, null, null, "Erreur lors de la validation: " + e.getMessage());
        }
    }
}
