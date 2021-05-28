/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform.controller;

import com.github.ricardobaumann.contentuxplatform.authorization.AccountRead;
import com.github.ricardobaumann.contentuxplatform.authorization.AccountWrite;
import com.github.ricardobaumann.contentuxplatform.commands.CourseData;
import com.github.ricardobaumann.contentuxplatform.commands.CreateCourseCommand;
import com.github.ricardobaumann.contentuxplatform.commands.CreateCourseResponse;
import com.github.ricardobaumann.contentuxplatform.mapper.CourseMapper;
import com.github.ricardobaumann.contentuxplatform.service.CourseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;

    @AccountWrite
    @PostMapping("/courses")
    public CreateCourseResponse create(@RequestBody CreateCourseCommand command) {
        log.info("create course: {}", command);
        return courseMapper.toResponse(courseService.create(command));
    }

    @AccountRead
    @GetMapping("/courses")
    public CourseData get(@PathVariable Long id) {
        return courseMapper.toCourseResponse(courseService.getByIdOrFail(id));
    }
    
}
