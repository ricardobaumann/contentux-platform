/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform.commands;

import lombok.Data;

@Data
public class GetAccountResponse {
    private Long id;
    private String accountCode;
    private String accountName;
}
