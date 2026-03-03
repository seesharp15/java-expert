package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.ProjectDependency;
import org.gradle.api.internal.artifacts.dependencies.ProjectDependencyInternal;
import org.gradle.api.internal.artifacts.DefaultProjectDependencyFactory;
import org.gradle.api.internal.artifacts.dsl.dependencies.ProjectFinder;
import org.gradle.api.internal.catalog.DelegatingProjectDependency;
import org.gradle.api.internal.catalog.TypeSafeProjectDependencyFactory;
import javax.inject.Inject;

@NonNullApi
public class ModulesProjectDependency extends DelegatingProjectDependency {

    @Inject
    public ModulesProjectDependency(TypeSafeProjectDependencyFactory factory, ProjectDependencyInternal delegate) {
        super(factory, delegate);
    }

    /**
     * Creates a project dependency on the project at path ":modules:immutability-concurrency"
     */
    public Modules_ImmutabilityConcurrencyProjectDependency getImmutabilityConcurrency() { return new Modules_ImmutabilityConcurrencyProjectDependency(getFactory(), create(":modules:immutability-concurrency")); }

    /**
     * Creates a project dependency on the project at path ":modules:interop"
     */
    public Modules_InteropProjectDependency getInterop() { return new Modules_InteropProjectDependency(getFactory(), create(":modules:interop")); }

    /**
     * Creates a project dependency on the project at path ":modules:io-serde"
     */
    public Modules_IoSerdeProjectDependency getIoSerde() { return new Modules_IoSerdeProjectDependency(getFactory(), create(":modules:io-serde")); }

    /**
     * Creates a project dependency on the project at path ":modules:kafka-reactive"
     */
    public Modules_KafkaReactiveProjectDependency getKafkaReactive() { return new Modules_KafkaReactiveProjectDependency(getFactory(), create(":modules:kafka-reactive")); }

    /**
     * Creates a project dependency on the project at path ":modules:language-basics"
     */
    public Modules_LanguageBasicsProjectDependency getLanguageBasics() { return new Modules_LanguageBasicsProjectDependency(getFactory(), create(":modules:language-basics")); }

    /**
     * Creates a project dependency on the project at path ":modules:performance-profiling"
     */
    public Modules_PerformanceProfilingProjectDependency getPerformanceProfiling() { return new Modules_PerformanceProfilingProjectDependency(getFactory(), create(":modules:performance-profiling")); }

    /**
     * Creates a project dependency on the project at path ":modules:persistence-jooq"
     */
    public Modules_PersistenceJooqProjectDependency getPersistenceJooq() { return new Modules_PersistenceJooqProjectDependency(getFactory(), create(":modules:persistence-jooq")); }

    /**
     * Creates a project dependency on the project at path ":modules:spring-boot-hexagonal"
     */
    public Modules_SpringBootHexagonalProjectDependency getSpringBootHexagonal() { return new Modules_SpringBootHexagonalProjectDependency(getFactory(), create(":modules:spring-boot-hexagonal")); }

    /**
     * Creates a project dependency on the project at path ":modules:streams-lambdas"
     */
    public Modules_StreamsLambdasProjectDependency getStreamsLambdas() { return new Modules_StreamsLambdasProjectDependency(getFactory(), create(":modules:streams-lambdas")); }

    /**
     * Creates a project dependency on the project at path ":modules:testing-quality"
     */
    public Modules_TestingQualityProjectDependency getTestingQuality() { return new Modules_TestingQualityProjectDependency(getFactory(), create(":modules:testing-quality")); }

}
