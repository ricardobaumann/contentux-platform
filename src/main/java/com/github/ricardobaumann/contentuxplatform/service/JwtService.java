/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.ricardobaumann.contentuxplatform.config.JwtProperties;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtProperties jwtProperties;
    private Algorithm algorithmHS;
    private JWTVerifier verifier;

    @PostConstruct
    public void init() {
        algorithmHS = Algorithm.HMAC256(jwtProperties.getSecret());
        verifier = JWT.require(algorithmHS)
                .withIssuer("contentux-platform")
                .build();
    }

    public String getTokenFor(CreateTokenRequest createTokenRequest) {
        return JWT.create()
                .withIssuer("contentux-platform")
                .withJWTId(createTokenRequest.getUsername())
                .withSubject(createTokenRequest.getUsername())
                .sign(algorithmHS);
    }

    public Optional<ParseTokenResponse> parseUserFrom(String token) {
        try {
            DecodedJWT jwt = verifier.verify(token);
            log.info("Decoded username subject from token: {}", jwt.getSubject());
            return Optional.ofNullable(jwt.getSubject())
                    .map(ParseTokenResponse::new);
        } catch (JWTVerificationException e) {
            log.warn("Unable to decode jwt: {}", token, e);
            return Optional.empty();
        }
    }

    @Value
    public static class CreateTokenRequest {
        String username;
    }

    @Value
    public static class ParseTokenResponse {
        String username;
    }
}
