package com.tugalsan.api.file.pom.server;

import com.tugalsan.api.list.client.TGS_ListUtils;
import java.nio.file.Path;
import java.util.List;

public class TS_FilePomConfig {

    private TS_FilePomConfig(Path mavenCodes, Path mavenRepository, Path mavenRepositoryProjects, List<CharSequence> projectGroupIds) {
        this.mavenCodes = mavenCodes;
        this.mavenRepository = mavenRepository;
        this.mavenRepositoryProjects = mavenRepositoryProjects;
        this.projectGroupIds = projectGroupIds;
    }
    public Path mavenCodes;
    public Path mavenRepository;
    public Path mavenRepositoryProjects;
    public List<CharSequence> projectGroupIds;

    public static TS_FilePomConfig of(Path mavenCodes, Path mavenRepository, Path mavenRepositoryProjects, CharSequence... projectGroupIds) {
        return new TS_FilePomConfig(mavenCodes, mavenRepository, mavenRepositoryProjects, TGS_ListUtils.of(projectGroupIds));
    }

    public static TS_FilePomConfig of() {
        return of(
                Path.of("C:\\me\\codes"),
                Path.of("C:\\Users\\tugal\\.m2\\repository"),
                Path.of("C:\\Users\\tugal\\.m2\\repository").resolve("com").resolve("tugalsan"),
                "com.tugalsan"
        );
    }
}
