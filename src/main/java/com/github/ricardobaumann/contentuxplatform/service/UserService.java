/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform.service;

import com.github.ricardobaumann.contentuxplatform.commands.CreateUserResponse;
import com.github.ricardobaumann.contentuxplatform.entity.Account;
import com.github.ricardobaumann.contentuxplatform.entity.User;
import com.github.ricardobaumann.contentuxplatform.mapper.UserMapper;
import com.github.ricardobaumann.contentuxplatform.repos.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public CreateUserResponse createAccountRootUserFor(Account account) {
        User user = User.builder()
                .account(account)
                .password(UUID.randomUUID().toString())
                .username(account.getAccountCode())
                .build();
        user.setPassword(UUID.randomUUID().toString());
        return userMapper.toCreateResponse(userRepository.save(user));
    }

}
