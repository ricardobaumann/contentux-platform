/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform.service;

import com.github.ricardobaumann.contentuxplatform.controller.UserRepository;
import com.github.ricardobaumann.contentuxplatform.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import java.util.Optional;

import static com.github.ricardobaumann.contentuxplatform.service.JwtService.CreateTokenRequest;
import static com.github.ricardobaumann.contentuxplatform.service.JwtService.ParseTokenResponse;

@Service
@AllArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public Optional<String> getBearerTokenFor(GetTokenRequest getTokenRequest) {
        return userRepository.findByUsername(getTokenRequest.getUsername())
                .filter(user -> user.passwordEqualTo(getTokenRequest.getPassword()))
                .map(User::getUsername)
                .map(CreateTokenRequest::new)
                .map(jwtService::getTokenFor);
    }

    public Optional<User> parseUserFrom(String bearerToken) {
        return jwtService.parseUserFrom(bearerToken)
                .map(ParseTokenResponse::getUsername)
                .flatMap(userRepository::findByUsername);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetTokenRequest {
        @NotEmpty
        private String username;
        @NotEmpty
        private String password;
    }
}
