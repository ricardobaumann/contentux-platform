/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform.commands;

import lombok.Data;
import org.springframework.http.MediaType;

import java.util.Set;

@Data
public class MediaData {
    private Long id;
    private MediaType mediaType;
    private String name;
    private String description;
    private Set<String> tags;
    private String accountCode;
}
