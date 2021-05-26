/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform.mapper;

import com.github.ricardobaumann.contentuxplatform.commands.CreateAccountCommand;
import com.github.ricardobaumann.contentuxplatform.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account toAccount(CreateAccountCommand createAccountCommand);
    
}
