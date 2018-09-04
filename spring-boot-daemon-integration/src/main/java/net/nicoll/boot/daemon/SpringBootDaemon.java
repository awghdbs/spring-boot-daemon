/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.nicoll.boot.daemon;

import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.ClassUtils;

/**
 * Basic {@link Daemon} implementation for a Spring Boot app. Only for
 * demonstration purposes as Spring Boot 1.3 has a much better support for
 * this.
 *
 * @author Stephane Nicoll
 */
class SpringBootDaemon implements Daemon {

    private Class<?> springBootApp;

    private ConfigurableApplicationContext content;

    @Override
    public void init(final DaemonContext context) {
        System.out.println(
                String.format("Daemon initialized with arguments [%s]",
                        Arrays.toString(context.getArguments())));
        this.springBootApp = ClassUtils.resolveClassName(
                context.getArguments()[0],
                SpringBootDaemon.class.getClassLoader());
    }

    @Override
    public void start() {
        System.out.println(
                String.format("Starting Spring Boot application [%s]",
                        this.springBootApp.getName()));
        this.content = SpringApplication.run(springBootApp);
    }

    @Override
    public void stop() {
        System.out.println(
                String.format("Stopping Spring Boot application [%s]",
                        this.springBootApp.getName()));
        this.content.close();
    }

    @Override
    public void destroy() {

    }

}
