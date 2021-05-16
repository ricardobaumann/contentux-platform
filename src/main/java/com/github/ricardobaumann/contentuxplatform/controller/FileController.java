/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform.controller;

import com.github.ricardobaumann.contentuxplatform.requests.FileUploadRequest;
import com.github.ricardobaumann.contentuxplatform.requests.MediaFileResource;
import com.github.ricardobaumann.contentuxplatform.service.FileService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.function.Function;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/media/{mediaId}/file")
public class FileController {

    private final FileService fileService;

    @PutMapping
    public void uploadFile(@RequestParam("file") MultipartFile file, @PathVariable Long mediaId) {
        fileService.upload(new FileUploadRequest(file, mediaId))
                .peek(media -> log.info("Media file {} uploaded successfully", media))
                .peekLeft(e -> log.error("Media file upload failed", e))
                .getOrElseThrow(Function.identity());
    }

    @GetMapping
    public ResponseEntity<Resource> getFile(@PathVariable Long mediaId) {
        return fileService.getFileResource(mediaId)
                .filter(MediaFileResource::hasFile)
                .map(mediaFileResource -> ResponseEntity.ok()
                        .headers(toHeaders(mediaFileResource))
                        .body(mediaFileResource.getResource()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @SneakyThrows
    private HttpHeaders toHeaders(MediaFileResource mediaFileResource) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaFileResource.getMedia().getMediaType());
        return headers;
    }
}
