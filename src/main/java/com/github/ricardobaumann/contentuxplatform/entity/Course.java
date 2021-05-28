/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "course")
@EqualsAndHashCode(of = "id", callSuper = false)
@EntityListeners(AuditingEntityListener.class)
@NamedEntityGraph(name = "Course.dep",
        attributeNodes = {
                @NamedAttributeNode("tags"),
                @NamedAttributeNode("courseClasses"),
                @NamedAttributeNode("account")
        })
public class Course extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ElementCollection
    @CollectionTable(
            name = "course_tags",
            joinColumns = @JoinColumn(name = "course_id")
    )
    @Column(name = "tag")
    private Set<String> tags;

    @RestResource
    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "course_class_links",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "course_class_id"))
    private Set<CourseClass> courseClasses;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

}
