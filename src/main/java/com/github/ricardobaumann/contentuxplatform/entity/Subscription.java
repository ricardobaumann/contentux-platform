package com.github.ricardobaumann.contentuxplatform.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "subscription_status")
    private SubscriptionStatus status = SubscriptionStatus.ACTIVE;

    @NotNull
    @Column(name = "subscription_type")
    private SubscriptionType type = SubscriptionType.MONTHLY;

    //account -> platform_admin
    //course -> account_admin for write, public for list/search and details. just class title
    //class -> account_admin for write, account_admin or active user for reading
    //media -> account_admin for write, account_admin or active user for reading

    //purchase -> if user is purchasing courses, provide /courses/my endpoint
    //specific annotations to check permissions on purchased resources
}