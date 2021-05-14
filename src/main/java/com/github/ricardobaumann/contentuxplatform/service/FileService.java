/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform.service;

import com.github.ricardobaumann.contentuxplatform.entity.Media;
import com.github.ricardobaumann.contentuxplatform.exceptions.FileUploadException;
import com.github.ricardobaumann.contentuxplatform.exceptions.MediaNotFoundException;
import com.github.ricardobaumann.contentuxplatform.repos.FileRepository;
import com.github.ricardobaumann.contentuxplatform.repos.MediaRepository;
import com.github.ricardobaumann.contentuxplatform.requests.FileUploadRequest;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class FileService {

    private final FileRepository fileRepository;
    private final MediaRepository mediaRepository;

    public Either<FileUploadException, Media> upload(FileUploadRequest fileUploadRequest) {
        return mediaRepository.findById(fileUploadRequest.getMediaId())
                .map(media -> fileRepository.writeFile(fileUploadRequest)
                        .map(fileWriteResult -> {
                            media.setFilePath(fileWriteResult.getPath());
                            media.setMediaType(
                                    Optional.ofNullable(fileUploadRequest.getFile().getContentType())
                                            .map(MediaType::valueOf)
                                            .orElse(null)
                            );
                            return mediaRepository.save(media);
                        }))
                .orElseGet(() -> Try.failure(new MediaNotFoundException(fileUploadRequest.getMediaId())))
                .toEither()
                .mapLeft(FileUploadException::new);
    }

    public Optional<MediaFileResource> getFileResource(Long mediaId) {
        return mediaRepository.findById(mediaId)
                .map(media -> new MediaFileResource(
                        fileRepository.getFileResourceFor(media.getFilePath())
                                .orElse(null),
                        media
                ));
    }

    @Value
    public static class MediaFileResource {
        Resource resource;
        Media media;

        public boolean hasFile() {
            return resource != null;
        }
    }
}
