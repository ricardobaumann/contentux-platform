/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform.authorization;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@PreAuthorize(AccountWrite.IS_COMMAND_ALLOWED)
public @interface AccountWrite {
    String IS_COMMAND_ALLOWED = "hasAuthority('platform_admin') " +
            "   or " +
            "   (hasAuthority('account_admin') " +
            "   and authentication.principal.account.accountCode == #command.accountCode)";
}
