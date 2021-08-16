/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform.config;

import com.github.ricardobaumann.contentuxplatform.entity.AuthenticatedUser;
import com.github.ricardobaumann.contentuxplatform.entity.User;
import com.github.ricardobaumann.contentuxplatform.authorization.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@SuppressWarnings("ALL")
@Slf4j
@Component
@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");

        log.info("Handling authorization: {}", authorization);

        Optional.ofNullable(authorization)
                .filter(s -> s.startsWith("Bearer "))
                .map(s -> s.substring(7))
                .flatMap(authService::parseUserFrom)
                .map(this::toAuthentication)
                .ifPresentOrElse(authentication -> SecurityContextHolder.getContext()
                                .setAuthentication(authentication),
                        SecurityContextHolder::clearContext);

        filterChain.doFilter(request, response);

    }

    private Authentication toAuthentication(User user) {
        UserDetails userDetails = new AuthenticatedUser(user);
        return new UsernamePasswordAuthenticationToken(
                userDetails, "",
                userDetails.getAuthorities());
    }

    private UserDetails toUserDetails(User user) {
        return new AuthenticatedUser(user);
    }
}
