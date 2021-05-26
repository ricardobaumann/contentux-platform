/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform.controller;

import com.github.ricardobaumann.contentuxplatform.commands.CreateAccountCommand;
import com.github.ricardobaumann.contentuxplatform.commands.CreateAccountResponse;
import com.github.ricardobaumann.contentuxplatform.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@PlatformAdminController
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/accounts")
    public CreateAccountResponse createAccount(@RequestBody CreateAccountCommand createAccountCommand) {
        return accountService.createAccount(createAccountCommand);
    }

}
