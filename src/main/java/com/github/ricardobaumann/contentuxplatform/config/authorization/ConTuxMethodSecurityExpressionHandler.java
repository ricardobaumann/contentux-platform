/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform.config.authorization;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

public class ConTuxMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

    private final transient AuthenticationTrustResolver authenticationTrustResolver =
            new AuthenticationTrustResolverImpl();

    private final transient ConTuxPermissionEvaluator conTuxPermissionEvaluator =
            new ConTuxPermissionEvaluator();

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(
            Authentication authentication,
            MethodInvocation invocation) {
        ConTuxMethodSecurityExpressionRoot root =
                new ConTuxMethodSecurityExpressionRoot(authentication);
        root.setPermissionEvaluator(conTuxPermissionEvaluator);
        root.setTrustResolver(authenticationTrustResolver);
        root.setRoleHierarchy(getRoleHierarchy());
        return root;
    }
}
