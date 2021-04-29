/*
 * Copyright 2020 the original author or authors.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.github.ricardobaumann.contentuxplatform;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    //db password poy0NpvBQZLrPRkHJwi7 postgres
    //contentux-db.cuv72rlqno5g.eu-west-1.rds.amazonaws.com
    @GetMapping("/test")
    public Test test() {
        return new Test("hello world");
    }

}
