package com.tugalsan.api.file.pom.server;

import java.util.List;

public record TS_FilePom(String groupId, String artifactId, List<TS_FilePomDependecy> dependecies) {

    public static TS_FilePom of(String groupId, String artifactId, List<TS_FilePomDependecy> dependecies) {
        return new TS_FilePom(groupId, artifactId, dependecies);
    }
}
