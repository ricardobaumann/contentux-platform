/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform.controller;

import com.github.ricardobaumann.contentuxplatform.authorization.AccountRead;
import com.github.ricardobaumann.contentuxplatform.authorization.AccountWrite;
import com.github.ricardobaumann.contentuxplatform.authorization.PlatformAdmin;
import com.github.ricardobaumann.contentuxplatform.commands.WriteClassCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/classes")
public class CourseClassController {

    @AccountWrite
    @PostMapping
    public ClassData create(@RequestBody WriteClassCommand command) {
        return new ClassData();
    }

    @AccountRead
    @GetMapping("/{id}")
    public ClassData get(@PathVariable Long id) {
        return new ClassData();
    }

    @GetMapping
    @PlatformAdmin
    public List<ClassData> get() {
        return Collections.emptyList();
    }

    @PutMapping("/{id}")
    public ClassData update(@RequestBody WriteClassCommand command) {
        //get class by id, authorize with service and update
        return new ClassData();
    }


}
