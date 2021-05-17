/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform;

import com.github.ricardobaumann.contentuxplatform.entity.*;
import com.github.ricardobaumann.contentuxplatform.repos.*;
import com.github.ricardobaumann.contentuxplatform.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class Init implements CommandLineRunner {

    private final CourseRepository courseRepository;
    private final CourseClassRepository courseClassRepository;
    private final UserRepository userRepository;
    private final MediaRepository mediaRepository;
    private final AuthService authService;
    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public void run(String... args) {

        userRepository.deleteAll();
        accountRepository.deleteAll();

        accountRepository.save(Account.builder()
                .accountCode(UUID.randomUUID().toString())
                .accountName("test-account")
                .build());

        userRepository.save(
                User.builder()
                        .roles(Set.of("platform_admin", "user"))
                        .password("test")
                        .username("test-user")
                        .account(accountRepository.findAll().iterator().next())
                        .build());

        log.info("token: {}", authService.getBearerTokenFor(new AuthService.GetTokenRequest("test-user", "test")));

        courseRepository.deleteAll();
        courseClassRepository.deleteAll();

        courseClassRepository.save(
                CourseClass.builder()
                        .body("some body")
                        .title("x men first class")
                        .build());

        courseRepository.save(
                Course.builder()
                        .title("first course")
                        .accountCode(
                                accountRepository.findAll().iterator().next().getAccountCode()
                        )
                        .build()
        );

        courseRepository.findAll()
                .forEach(course -> log.info("Course: {}", course));

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
