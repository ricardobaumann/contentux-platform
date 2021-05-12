/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform.entity;

import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;
import org.springframework.http.MediaType;

public class MediaStringType extends AbstractSingleColumnStandardBasicType<MediaType> {

    private static final MediaStringType MEDIA_STRING_TYPE = new MediaStringType();

    public MediaStringType() {
        super(VarcharTypeDescriptor.INSTANCE, MediaStringTypeDescriptor.INSTANCE);
    }

    @Override
    public String getName() {
        return "Media Type";
    }
}
