/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform.requests;

import com.github.ricardobaumann.contentuxplatform.entity.Media;
import lombok.Value;
import org.springframework.core.io.Resource;

@Value
public class MediaFileResource {
    Resource resource;
    Media media;

    public boolean hasFile() {
        return resource != null;
    }
}
