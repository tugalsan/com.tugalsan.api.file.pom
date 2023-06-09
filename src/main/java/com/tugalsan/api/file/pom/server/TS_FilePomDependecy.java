package com.tugalsan.api.file.pom.server;

public record TS_FilePomDependecy(String groupId, String artifactId) {

    public static TS_FilePomDependecy of(String groupId, String artifactId) {
        return new TS_FilePomDependecy(groupId, artifactId);
    }
}
