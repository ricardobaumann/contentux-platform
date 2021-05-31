/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform.controller;

import com.github.ricardobaumann.contentuxplatform.authorization.AccountRead;
import com.github.ricardobaumann.contentuxplatform.commands.AccountData;
import com.github.ricardobaumann.contentuxplatform.commands.CreateAccountCommand;
import com.github.ricardobaumann.contentuxplatform.commands.CreateAccountResponse;
import com.github.ricardobaumann.contentuxplatform.entity.AuthenticatedUser;
import com.github.ricardobaumann.contentuxplatform.mapper.AccountMapper;
import com.github.ricardobaumann.contentuxplatform.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final AccountMapper accountMapper;

    @PostMapping("/accounts")
    @PreAuthorize("hasAuthority('platform_admin')")
    public CreateAccountResponse createAccount(@RequestBody CreateAccountCommand createAccountCommand,
                                               @AuthenticationPrincipal AuthenticatedUser user) {
        log.info("principal: {}", user);
        return accountService.createAccount(createAccountCommand);
    }

    @AccountRead
    @GetMapping("/accounts/{id}")
    public AccountData getAccount(@PathVariable Long id,
                                  @AuthenticationPrincipal AuthenticatedUser user) {
        log.info("auth user: {}", user);
        return accountService.getById(id)
                .map(accountMapper::toGetResponse)
                .map(accountData -> {
                    log.info("account: {}", accountData);
                    return accountData;
                })
                .orElse(null);
    }

}
