/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform.entity;

import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor;
import org.hibernate.type.descriptor.java.ImmutableMutabilityPlan;
import org.hibernate.type.descriptor.java.JavaTypeDescriptor;
import org.springframework.http.MediaType;

import java.util.Optional;

@SuppressWarnings("unchecked")
public class MediaStringTypeDescriptor extends AbstractTypeDescriptor<MediaType> {
    public static final JavaTypeDescriptor<MediaType> INSTANCE = new MediaStringTypeDescriptor();

    protected MediaStringTypeDescriptor() {
        super(MediaType.class, ImmutableMutabilityPlan.INSTANCE);
    }

    @Override
    public MediaType fromString(String string) {
        return Optional.ofNullable(string)
                .map(MediaType::valueOf)
                .orElse(null);
    }

    @Override
    public <X> X unwrap(MediaType value, Class<X> type, WrapperOptions options) {
        return Optional.ofNullable(value)
                .map(mediaType -> (X) mediaType.toString())
                .orElse(null);
    }

    @Override
    public <X> MediaType wrap(X value, WrapperOptions options) {
        return Optional.ofNullable(value)
                .map(x -> MediaType.valueOf(x.toString()))
                .orElse(null);
    }
}
