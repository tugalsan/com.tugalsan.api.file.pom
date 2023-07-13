package com.tugalsan.api.file.pom.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record TS_FilePom(String groupId, String artifactId, List<TS_FilePomDependecy> dependecies) {

    public static TS_FilePom of(String groupId, String artifactId, List<TS_FilePomDependecy> dependecies) {
        return new TS_FilePom(groupId, artifactId, dependecies);
    }

    public static Optional<TS_FilePom> of(TS_FilePomConfig cfg, String groupId, String artifactId) {
cfg.mavenRepositoryProjects
        
        List<TS_FilePomDependecy> dependecies = new ArrayList();
        return Optional.of(TS_FilePom.of(groupId, artifactId, dependecies));
    }
}
