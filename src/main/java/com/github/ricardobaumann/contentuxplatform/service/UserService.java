/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform.service;

import com.github.ricardobaumann.contentuxplatform.commands.CreateRegistrationCommand;
import com.github.ricardobaumann.contentuxplatform.commands.CreateUserResponse;
import com.github.ricardobaumann.contentuxplatform.entity.*;
import com.github.ricardobaumann.contentuxplatform.repos.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public CreateUserResponse createAccountRootUserFor(Account account) {
        String password = UUID.randomUUID().toString()
                .replaceAll("\\-", "");

        User user = userRepository.save(
                User.builder()
                        .account(account)
                        .password(password)
                        .username(account.getAccountCode())
                        .roles(Set.of("account_root", "account_admin", "user"))
                        .password(password)
                        .build()
        );
        return new CreateUserResponse(user.getId(), user.getUsername(), password);
    }

    public User registerUser(CreateRegistrationCommand command) {
        return userRepository.save(
                User.builder()
                        .password(command.getPassword())
                        .username(command.getUsername())
                        .subscription(
                                Subscription.builder()
                                        .status(SubscriptionStatus.ACTIVE)
                                        .type(SubscriptionType.MONTHLY)
                                        .build())
                        .build()
        );
    }

    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }
}
