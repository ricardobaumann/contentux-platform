/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform;

import com.github.ricardobaumann.contentuxplatform.controller.CourseController;
import com.github.ricardobaumann.contentuxplatform.entity.Course;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class Init implements CommandLineRunner {

    private final CourseController courseController;

    @Override
    public void run(String... args) {
        courseController.deleteAll();
        courseController.save(Course.builder().title("first course").build());
    }
}
