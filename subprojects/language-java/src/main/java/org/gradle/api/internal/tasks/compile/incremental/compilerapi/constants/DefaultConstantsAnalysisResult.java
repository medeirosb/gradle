/*
 * Copyright 2021 the original author or authors.
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

package org.gradle.api.internal.tasks.compile.incremental.compilerapi.constants;

import java.io.Serializable;
import java.util.Optional;

public class DefaultConstantsAnalysisResult implements ConstantsAnalysisResult, Serializable {

    private final ConstantToDependentsMappingBuilder constantToDependentsMappingBuilder;

    public DefaultConstantsAnalysisResult() {
        this.constantToDependentsMappingBuilder = ConstantToDependentsMapping.builder();
    }

    public Optional<ConstantToDependentsMapping> getConstantToDependentsMapping() {
        return Optional.of(constantToDependentsMappingBuilder.build());
    }

    public void addPublicDependent(String constantOrigin, String constantDependent) {
        if (constantOrigin.equals(constantDependent)) {
            // Don't add self as dependent but just as visited
            constantToDependentsMappingBuilder.addVisitedClass(constantDependent);
        } else {
            constantToDependentsMappingBuilder.addAccessibleDependent(constantOrigin, constantDependent);
        }
    }

    public void addPrivateDependent(String constantOrigin, String constantDependent) {
        if (constantOrigin.equals(constantDependent)) {
            // Don't add self as dependent but just as visited
            constantToDependentsMappingBuilder.addVisitedClass(constantDependent);
        } else {
            constantToDependentsMappingBuilder.addPrivateDependent(constantOrigin, constantDependent);
        }
    }

}