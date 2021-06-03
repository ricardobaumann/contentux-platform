/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform.controller;

import com.github.ricardobaumann.contentuxplatform.authorization.AccountRead;
import com.github.ricardobaumann.contentuxplatform.authorization.AccountWrite;
import com.github.ricardobaumann.contentuxplatform.authorization.AuthorizationService;
import com.github.ricardobaumann.contentuxplatform.authorization.PlatformAdmin;
import com.github.ricardobaumann.contentuxplatform.commands.CourseData;
import com.github.ricardobaumann.contentuxplatform.commands.CreateCourseResponse;
import com.github.ricardobaumann.contentuxplatform.commands.WriteCourseCommand;
import com.github.ricardobaumann.contentuxplatform.entity.AuthenticatedUser;
import com.github.ricardobaumann.contentuxplatform.entity.Course;
import com.github.ricardobaumann.contentuxplatform.mapper.CourseMapper;
import com.github.ricardobaumann.contentuxplatform.service.CourseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@RestController
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;
    private final AuthorizationService authorizationService;

    @AccountWrite
    @PostMapping("/courses")
    public CreateCourseResponse create(@RequestBody WriteCourseCommand command) {
        log.info("create course: {}", command);
        return courseMapper.toResponse(courseService.create(command));
    }

    @AccountRead
    @GetMapping("/courses/{id}")
    public CourseData get(@PathVariable Long id) {
        return courseMapper.toCourseResponse(getById(id));
    }

    @PlatformAdmin
    @GetMapping("/courses")
    public List<CourseData> getAll() {
        return StreamSupport.stream(courseService.getAll().spliterator(), false)
                .map(courseMapper::toCourseResponse)
                .collect(Collectors.toList());
    }

    private Course getById(Long id) {
        return courseService.getByIdOrFail(id);
    }

    @PutMapping("/courses/{id}")
    public CourseData update(@PathVariable Long id,
                             @RequestBody WriteCourseCommand command,
                             @AuthenticationPrincipal AuthenticatedUser user) {
        Course course = getById(id);
        authorizationService.failIfNotWriteAllowed(course, user);
        return courseMapper.toCourseResponse(courseService.update(course, command));

    }

}
