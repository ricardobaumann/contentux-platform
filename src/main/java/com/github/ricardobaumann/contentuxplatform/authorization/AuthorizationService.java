/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform.authorization;

import com.github.ricardobaumann.contentuxplatform.entity.AuthenticatedUser;
import com.github.ricardobaumann.contentuxplatform.entity.Course;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthorizationService {
    public void failIfNotWriteAllowed(Course course, AuthenticatedUser user) {
        if (!(user.hasAuthority("platform_admin") ||
                (user.hasAuthority("account_admin")
                        && user.getAccount().equals(course.getAccount())))) {
            throw new AccessDeniedException("Unauthorized");
        }
    }
}
