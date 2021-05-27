/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform.service;

import com.github.ricardobaumann.contentuxplatform.commands.CreateAccountCommand;
import com.github.ricardobaumann.contentuxplatform.commands.CreateAccountResponse;
import com.github.ricardobaumann.contentuxplatform.commands.CreateUserResponse;
import com.github.ricardobaumann.contentuxplatform.entity.Account;
import com.github.ricardobaumann.contentuxplatform.mapper.AccountMapper;
import com.github.ricardobaumann.contentuxplatform.repos.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final UserService userService;

    @Transactional
    public CreateAccountResponse createAccount(@Valid CreateAccountCommand createAccountCommand) {
        Account account = accountRepository.save(accountMapper.toAccount(createAccountCommand));
        CreateUserResponse createUserResponse = userService.createAccountRootUserFor(account);
        return new CreateAccountResponse(account.getId(), createUserResponse);
    }

    public Optional<Account> getById(Long id) {
        return accountRepository.findById(id);
    }

}
