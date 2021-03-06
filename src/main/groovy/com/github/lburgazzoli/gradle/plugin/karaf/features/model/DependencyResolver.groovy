/**
 * Copyright 2016, Luca Burgazzoli and contributors as indicated by the @author tags
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.lburgazzoli.gradle.plugin.karaf.features.model

import com.github.lburgazzoli.gradle.plugin.karaf.features.KarafFeaturesUtils

/**
 * @author lburgazzoli
 */
abstract class DependencyResolver {

    Collection<DependencyDescriptor> resolve(FeatureDescriptor featureDescriptor) {
        def deps = KarafFeaturesUtils.collectDependencies(
            featureDescriptor,
            new LinkedHashSet<>()
        )

        return deps.each {
            it.url = renderUrl(it)
        }.findAll {
            it.isResolved()
        }
    }

    protected abstract String renderUrl(DependencyDescriptor dependency)
}