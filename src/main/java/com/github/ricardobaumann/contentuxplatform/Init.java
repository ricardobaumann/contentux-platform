/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform;

import com.github.ricardobaumann.contentuxplatform.entity.Course;
import com.github.ricardobaumann.contentuxplatform.entity.CourseClass;
import com.github.ricardobaumann.contentuxplatform.entity.Media;
import com.github.ricardobaumann.contentuxplatform.entity.User;
import com.github.ricardobaumann.contentuxplatform.repos.CourseClassRepository;
import com.github.ricardobaumann.contentuxplatform.repos.CourseRepository;
import com.github.ricardobaumann.contentuxplatform.repos.MediaRepository;
import com.github.ricardobaumann.contentuxplatform.repos.UserRepository;
import com.github.ricardobaumann.contentuxplatform.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
@AllArgsConstructor
public class Init implements CommandLineRunner {

    private final CourseRepository courseRepository;
    private final CourseClassRepository courseClassRepository;
    private final UserRepository userRepository;
    private final MediaRepository mediaRepository;
    private final AuthService authService;

    @Override
    public void run(String... args) {

        userRepository.deleteAll();
        userRepository.save(User.builder()
                .roles(Set.of("admin", "user"))
                .password("test")
                .username("test-user")
                .build());

        log.info("token: {}", authService.getBearerTokenFor(new AuthService.GetTokenRequest("test-user", "test")));

        courseRepository.deleteAll();
        courseClassRepository.deleteAll();

        courseClassRepository.save(CourseClass.builder()
                .body("some body")
                .title("x men first class")
                .build());

        courseRepository.save(Course.builder()
                .title("first course")
                .build()
        );
        mediaRepository.deleteAll();
        mediaRepository.save(
                Media.builder()
                        .mediaType(MediaType.APPLICATION_ATOM_XML)
                        .description("test")
                        .filePath("path")
                        .description("nice media")
                        .name("test-media")
                        .tags(Set.of("tag1", "tag2"))
                        .build()
        );

        mediaRepository.findAll()
                .forEach(media -> log.info(media.toString()));
    }
    /*
    curl -i -X PUT -d "http://localhost:8080/courseClasses/1" -H "Content-Type:text/uri-list" http://localhost:8080/courses/4/courseClasses
    curl -i -X PUT -d "https://desolate-scrubland-00574.herokuapp.com/courseClasses/1" -H "Content-Type:text/uri-list" https://desolate-scrubland-00574.herokuapp.com/courses/2/courseClasses
     */
}
