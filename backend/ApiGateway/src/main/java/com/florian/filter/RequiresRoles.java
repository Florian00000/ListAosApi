package com.florian.filter;

import jakarta.ws.rs.NameBinding;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@NameBinding //permet d'appliquer le filtre uniquement aux routes qui ont l'annotation
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresRoles {
    String[] value() default {};

    /**
     * Si true, l'utilisateur doit avoir TOUS les rôles spécifiés
     * Si false, l'utilisateur doit avoir AU MOINS UN des rôles spécifiés
     */
    boolean requireAll() default false;
}
