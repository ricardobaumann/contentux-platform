/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform.controller;

import com.github.ricardobaumann.contentuxplatform.authorization.AuthorizationService;
import com.github.ricardobaumann.contentuxplatform.commands.MediaData;
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

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/media")
public class MediaController {

    private final FileService fileService;
    private final AuthorizationService authorizationService;

    @PostMapping
    public MediaData uploadFile(@RequestParam("file") MultipartFile file,
                                @RequestHeader Long classId,
                                @RequestHeader(required = false) String name,
                                @RequestHeader(required = false) String description,
                                @RequestHeader(required = false) String tags) {

        //is user authorized to create media on that class?
        //upload file and create media
        return new MediaData();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getFile(@PathVariable Long id) {
        return null;
    }

    @SneakyThrows
    private HttpHeaders toHeaders(MediaFileResource mediaFileResource) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaFileResource.getMedia().getMediaType());
        return headers;
    }
}
