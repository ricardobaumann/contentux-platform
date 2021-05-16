/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform.repos;

import com.github.ricardobaumann.contentuxplatform.entity.Media;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface MediaRepository extends CrudRepository<Media, Long> {

    @Override
    @EntityGraph(value = "Media.tags")
    Optional<Media> findById(Long id);

    @Override
    @EntityGraph(value = "Media.tags")
    Iterable<Media> findAll();
}
