package com.dh.cryptocurrency.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KeyCloakJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private final JwtGrantedAuthoritiesConverter defaultGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    @Override
    public AbstractAuthenticationToken convert(Jwt source) {
        Collection<GrantedAuthority> authorities = null;

        try {
            authorities = this.getGrantedAuthorities(source);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JwtAuthenticationToken(source, authorities);
    }

    public static List<GrantedAuthority> extractAud(String route, JsonNode jwt) {
        Set<String> rolesWithPrefix = new HashSet<>();

        jwt.path(route).path("roles")
                .elements()
                .forEachRemaining(e -> rolesWithPrefix.add("AUD_" + e.asText()));

        return AuthorityUtils.createAuthorityList(rolesWithPrefix.toArray(rolesWithPrefix.toArray(new String[0])));

    }

    public static List<GrantedAuthority> extractRoles(String route, JsonNode jwt) {
        Set<String> rolesWithPrefix = new HashSet<>();

        jwt.path(route).path("roles")
                .elements()
                .forEachRemaining(e -> rolesWithPrefix.add("ROLE_" + e.asText()));

        return AuthorityUtils.createAuthorityList(rolesWithPrefix.toArray(rolesWithPrefix.toArray(new String[0])));

    }

    private static Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) throws JsonProcessingException {
        Set<GrantedAuthority> authorities = new HashSet<>();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        JsonNode node = mapper.readTree(mapper.writeValueAsString(jwt)).get("claims");

        assert node != null;

        authorities.addAll(extractRoles("resource_access", node));
        authorities.addAll(extractRoles("realm_access", node));
        authorities.addAll(extractAud("aud", node));

        return authorities;
    }

    public Collection<GrantedAuthority> getGrantedAuthorities(Jwt source) throws JsonProcessingException {
        return Stream.concat(
                        this.defaultGrantedAuthoritiesConverter.convert(source).stream(), extractResourceRoles(source).stream())
                .collect(Collectors.toSet());
    }
}
