/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform.requests;

import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Value
public class FileUploadRequest {
    @NotNull
    MultipartFile file;
    @NotNull
    Long mediaId;
}
