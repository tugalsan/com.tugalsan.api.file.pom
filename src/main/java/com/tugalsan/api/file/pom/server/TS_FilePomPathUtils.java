package com.tugalsan.api.file.pom.server;

import com.tugalsan.api.file.server.TS_FileUtils;
import static java.lang.System.out;
import java.nio.file.Path;
import java.util.Optional;

public class TS_FilePomPathUtils {

    private static Path drive() {
        return Path.of("C:\\");
    }

    private static Path me() {
        return drive().resolve("me");
    }

    private static Path codes() {
        return me().resolve("codes");
    }

    public static String strGroupId() {
        return "com.tugalsan";
    }

    public static Path ofGroupId() {
        return codes().resolve(strGroupId());
    }

    public static Optional<Path> ofByArtifactId(String artifactId) {
        if (!artifactId.startsWith(strGroupId())) {
            out.println("WARNING skip artifact:" + artifactId);
            return Optional.empty();
        }
        var slice = artifactId.substring(strGroupId().length() + 1);
        var idx_dot = slice.indexOf(".");
        if (idx_dot == -1) {
            out.println("ERROR idx_dot == -1:" + artifactId);
            return Optional.empty();
        }
        var subDirName = slice.substring(0, idx_dot);
        var pom_xml = ofGroupId().resolve(subDirName).resolve(artifactId).resolve("pom.xml");
        if (!TS_FileUtils.isExistFile(pom_xml)){
            out.println("ERRIR FILE NOT FOUND:" + pom_xml);
        }
        return Optional.of(pom_xml);
    }
}
