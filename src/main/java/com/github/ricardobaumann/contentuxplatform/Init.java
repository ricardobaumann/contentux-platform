/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform;

import com.github.ricardobaumann.contentuxplatform.controller.CourseClassController;
import com.github.ricardobaumann.contentuxplatform.controller.CourseController;
import com.github.ricardobaumann.contentuxplatform.controller.UserController;
import com.github.ricardobaumann.contentuxplatform.entity.Course;
import com.github.ricardobaumann.contentuxplatform.entity.CourseClass;
import com.github.ricardobaumann.contentuxplatform.entity.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
@AllArgsConstructor
public class Init implements CommandLineRunner {

    private final CourseController courseController;
    private final CourseClassController courseClassController;
    private final UserController userController;

    @Override
    public void run(String... args) {

        userController.deleteAll();
        userController.save(User.builder()
                .roles(Set.of("admin", "user"))
                .username("test-user")
                .build());

        courseController.deleteAll();
        courseClassController.deleteAll();

        courseClassController.save(CourseClass.builder()
                .body("some body")
                .title("x men first class")
                .build());

        courseController.save(Course.builder()
                .title("first course")
                .build()
        );
    }
    /*
    curl -i -X PUT -d "http://localhost:8080/courseClasses/1" -H "Content-Type:text/uri-list" http://localhost:8080/courses/4/courseClasses
    curl -i -X PUT -d "https://desolate-scrubland-00574.herokuapp.com/courseClasses/1" -H "Content-Type:text/uri-list" https://desolate-scrubland-00574.herokuapp.com/courses/2/courseClasses
     */
}
