/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform.service;

import com.github.ricardobaumann.contentuxplatform.commands.WriteCourseCommand;
import com.github.ricardobaumann.contentuxplatform.entity.Course;
import com.github.ricardobaumann.contentuxplatform.exceptions.CourseNotFoundException;
import com.github.ricardobaumann.contentuxplatform.exceptions.PlatformAccountNotFoundException;
import com.github.ricardobaumann.contentuxplatform.repos.CourseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Slf4j
@Service
@AllArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final AccountService accountService;

    public Course create(@Valid WriteCourseCommand command) {
        return courseRepository.save(
                Course.builder()
                        .account(accountService.getByAccountCode(command.getAccountCode())
                                .orElseThrow(() -> new PlatformAccountNotFoundException(
                                        command.getAccountCode())))
                        .tags(command.getTags())
                        .title(command.getTitle())
                        .build());
    }

    public Course getByIdOrFail(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
    }

    public Course update(Course course, WriteCourseCommand command) {


        return course;
    }
}
