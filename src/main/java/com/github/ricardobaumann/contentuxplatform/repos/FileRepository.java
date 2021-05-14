/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform.repos;

import com.github.ricardobaumann.contentuxplatform.requests.FileUploadRequest;
import io.vavr.control.Try;
import lombok.Value;
import org.springframework.core.io.Resource;

import java.util.Optional;

public interface FileRepository {
    Try<FileWriteResult> writeFile(FileUploadRequest fileUploadRequest);

    Optional<Resource> getFileResourceFor(String filePath);

    @Value
    class FileWriteResult {
        String path;
    }
}
