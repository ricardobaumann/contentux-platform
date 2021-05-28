/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.NotNull;

@ResponseStatus(HttpStatus.CONFLICT)
public class PlatformAccountNotFoundException extends RuntimeException {
    public PlatformAccountNotFoundException(@NotNull String account) {
        super("Account not found: " + account);
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
