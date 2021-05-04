/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "platform_user")
@EqualsAndHashCode(of = "id", callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public class User extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String username;

    @ElementCollection
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles;

    @JsonIgnore
    private String password;

    @PrePersist
    public void prePersist() {
        setPassword(Base64.getEncoder().encodeToString(getPassword().getBytes(StandardCharsets.UTF_8)));
    }

    public boolean passwordEqualTo(String password) {
        return Base64.getEncoder().encodeToString(
                password.getBytes(StandardCharsets.UTF_8))
                .equals(getPassword());
    }

}