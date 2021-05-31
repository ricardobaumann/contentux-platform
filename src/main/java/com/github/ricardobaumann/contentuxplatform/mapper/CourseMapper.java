/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform.mapper;

import com.github.ricardobaumann.contentuxplatform.commands.CourseData;
import com.github.ricardobaumann.contentuxplatform.commands.CreateCourseResponse;
import com.github.ricardobaumann.contentuxplatform.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CreateCourseResponse toResponse(Course course);

    @Mapping(source = "account.accountCode", target = "accountCode")
    CourseData toCourseResponse(Course course);
}
