/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform.controller;

import com.github.ricardobaumann.contentuxplatform.commands.CreateCourseCommand;
import com.github.ricardobaumann.contentuxplatform.commands.CreateCourseResponse;
import com.github.ricardobaumann.contentuxplatform.service.CourseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @CanWriteOnAccount
    @PostMapping("/courses")
    public CreateCourseResponse create(@RequestBody CreateCourseCommand command) {
        log.info("create course: {}", command);
        return null;
    }

}
