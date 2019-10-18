/*
 * Copyright 2017-2019 T-Doer (tdoer.com).
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
package com.tdoer.bedrock;

import org.springframework.util.Assert;

/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public class CloudEnvironmentHolder {
    // ~ Static fields/initializers
    // =====================================================================================

    public static final String MODE_THREADLOCAL = "MODE_THREADLOCAL";
    public static final String MODE_INHERITABLETHREADLOCAL = "MODE_INHERITABLETHREADLOCAL";
    public static final String SYSTEM_PROPERTY = "cloud.environment.strategy";
    private static String strategyName = System.getProperty(SYSTEM_PROPERTY);
    private static CloudEnvironmentHolderStrategy strategy;
    private static int initializeCount = 0;

    static {
        initialize();
    }

    // ~ Methods
    // ========================================================================================================

    /**
     * Explicitly clears the environment value from the current thread.
     */
    public static void clearEnvironment() {
        strategy.clearEnvironment();
    }

    /**
     * Obtain the current <code>CloudEnvironment</code>.
     *
     * @return the security environment (never <code>null</code>)
     */
    public static CloudEnvironment getEnvironment() {
        return strategy.getEnvironment();
    }

    /**
     * Primarily for troubleshooting purposes, this method shows how many times the class
     * has re-initialized its <code>CloudEnvironmentHolderStrategy</code>.
     *
     * @return the count (should be one unless you've called
     * {@link #setStrategyName(String)} to switch to an alternate strategy.
     */
    public static int getInitializeCount() {
        return initializeCount;
    }

    private static void initialize() {

        if (MODE_INHERITABLETHREADLOCAL.equals(strategyName)) {
            strategy = new InheritableThreadLocalCloudEnvironmentHolderStrategy();
        } else {
            strategy = new ThreadLocalCloudEnvironmentHolderStrategy();
        }

        initializeCount++;
    }

    /**
     * Associates a new <code>CloudEnvironment</code> with the current thread of execution.
     *
     * @param environment the new <code>CloudEnvironment</code> (may not be <code>null</code>)
     */
    public static void setEnvironment(CloudEnvironment environment) {
        strategy.setEnvironment(environment);
    }

    /**
     * Changes the preferred strategy. Do <em>NOT</em> call this method more than once for
     * a given JVM, as it will re-initialize the strategy and adversely affect any
     * existing threads using the old strategy.
     *
     * @param strategyName the fully qualified class name of the strategy that should be
     *                     used.
     */
    public static void setStrategyName(String strategyName) {
        CloudEnvironmentHolder.strategyName = strategyName;
        initialize();
    }

    /**
     * Allows retrieval of the environment strategy. See SEC-1188.
     *
     * @return the configured strategy for storing the security environment.
     */
    public static CloudEnvironmentHolderStrategy getEnvironmentHolderStrategy() {
        return strategy;
    }

    @Override
    public String toString() {
        return "CloudEnvironmentHolder[strategy='" + strategyName + "'; initializeCount="
                + initializeCount + "]";
    }

    private interface CloudEnvironmentHolderStrategy {
        /**
         * Clears the current environment.
         */
        void clearEnvironment();

        /**
         * Obtains the current environment.
         *
         * @return a environment (never <code>null</code> - create a default implementation if
         * necessary)
         */
        CloudEnvironment getEnvironment();

        /**
         * Sets the current environment.
         *
         * @param environment to the new argument (should never be <code>null</code>, although
         *                    implementations must check if <code>null</code> has been passed and throw an
         *                    <code>IllegalArgumentException</code> in such cases)
         */
        void setEnvironment(CloudEnvironment environment);
    }

    static private class InheritableThreadLocalCloudEnvironmentHolderStrategy implements
            CloudEnvironmentHolderStrategy {
        // ~ Static fields/initializers
        // =====================================================================================

        private static final ThreadLocal<CloudEnvironment> environmentHolder = new InheritableThreadLocal<>();

        // ~ Methods
        // ========================================================================================================

        public void clearEnvironment() {
            environmentHolder.remove();
        }

        public CloudEnvironment getEnvironment() {
            CloudEnvironment ctx = environmentHolder.get();

            if (ctx == null) {
                throw new IllegalStateException("Environment is not set yet");
            }

            return ctx;
        }

        public void setEnvironment(CloudEnvironment environment) {
            Assert.notNull(environment, "Only non-null CloudEnvironment instances are permitted");
            environmentHolder.set(environment);
        }

    }

    static private class ThreadLocalCloudEnvironmentHolderStrategy implements
            CloudEnvironmentHolderStrategy {
        // ~ Static fields/initializers
        // =====================================================================================

        private static final ThreadLocal<CloudEnvironment> environmentHolder = new ThreadLocal<>();

        // ~ Methods
        // ========================================================================================================

        public void clearEnvironment() {
            environmentHolder.remove();
        }

        public CloudEnvironment getEnvironment() {
            CloudEnvironment ctx = environmentHolder.get();

            if (ctx == null) {
                throw new IllegalStateException("Environment is not set yet");
            }

            return ctx;
        }

        public void setEnvironment(CloudEnvironment environment) {
            Assert.notNull(environment, "Only non-null CloudEnvironment instances are permitted");
            environmentHolder.set(environment);
        }

    }
}
