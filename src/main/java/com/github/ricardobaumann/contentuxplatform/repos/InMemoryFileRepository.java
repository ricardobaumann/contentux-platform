/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform.repos;

import com.github.ricardobaumann.contentuxplatform.requests.FileUploadRequest;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class InMemoryFileRepository implements FileRepository {

    private final Map<String, Resource> resourceMap = new HashMap<>();

    @Override
    public Try<FileWriteResult> writeFile(FileUploadRequest fileUploadRequest) {
        return Try.of(() -> {
            String path = String.format("media/%d/%s",
                    fileUploadRequest.getMediaId(),
                    fileUploadRequest.getFile().getOriginalFilename()
            );
            resourceMap.put(path, new ByteArrayResource(fileUploadRequest.getFile().getBytes()));
            return new FileWriteResult(path);
        });
    }

    @Override
    public Optional<Resource> getFileResourceFor(String filePath) {
        return Optional.ofNullable(resourceMap.get(filePath));
    }

}
