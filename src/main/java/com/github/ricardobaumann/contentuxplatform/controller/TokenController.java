/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform.controller;

import com.github.ricardobaumann.contentuxplatform.entity.User;
import com.github.ricardobaumann.contentuxplatform.authorization.AuthService;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.github.ricardobaumann.contentuxplatform.authorization.AuthService.GetTokenRequest;

@Slf4j
@RestController
@AllArgsConstructor
public class TokenController {

    private final AuthService authService;

    @PostMapping("/token")
    public ResponseEntity<TokenResponse> getBearerToken(@RequestBody @Valid GetTokenRequest getTokenRequest) {
        log.info("Received token request: {}", getTokenRequest);
        return authService.getBearerTokenFor(getTokenRequest)
                .map(TokenResponse::new)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @GetMapping("/token/{token}")
    public ResponseEntity<User> authenticate(@PathVariable String token) {
        return authService.parseUserFrom(token)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @Value
    public static class TokenResponse {
        String bearerToken;
    }
}
