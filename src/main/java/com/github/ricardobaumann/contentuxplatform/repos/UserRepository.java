/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform.repos;

import com.github.ricardobaumann.contentuxplatform.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @EntityGraph(value = "User.dep")
    Optional<User> findByUsername(String username);

    @Override
    @EntityGraph(value = "User.dep")
    Optional<User> findById(Long aLong);

    @Override
    @EntityGraph(value = "User.dep")
    Iterable<User> findAll();

    @Override
    @EntityGraph(value = "User.dep")
    Iterable<User> findAllById(Iterable<Long> longs);
}
