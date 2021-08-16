/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform.controller;

import com.github.ricardobaumann.contentuxplatform.commands.CreateRegistrationCommand;
import com.github.ricardobaumann.contentuxplatform.commands.RegistrationData;
import com.github.ricardobaumann.contentuxplatform.mapper.UserMapper;
import com.github.ricardobaumann.contentuxplatform.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/registrations")
public class RegistrationController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<RegistrationData> create(@RequestBody CreateRegistrationCommand command,
                                                   UriComponentsBuilder b) {
        RegistrationData registrationData = userMapper.toRegistrationData(userService.registerUser(command));
        return ResponseEntity.created(
                        b.path("/registrations/{id}")
                                .buildAndExpand(
                                        registrationData.getId().toString()).toUri())
                .body(registrationData);
    }

    @GetMapping("/{id}")
    @PostAuthorize("authentication.principal.id == #id")
    public ResponseEntity<RegistrationData> get(@PathVariable Long id) {
        return userService.getById(id)
                .map(userMapper::toRegistrationData)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
