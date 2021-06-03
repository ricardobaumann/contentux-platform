/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform.controller;

import com.github.ricardobaumann.contentuxplatform.entity.Account;
import lombok.Data;

@Data
public class ClassData {
    private Long id;
    private String title;
    private String body;
    private Account accountCode;
}
