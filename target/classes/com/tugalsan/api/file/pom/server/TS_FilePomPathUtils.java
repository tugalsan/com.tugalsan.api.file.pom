package com.tugalsan.api.file.pom.server;

import com.tugalsan.api.file.server.TS_DirectoryUtils;
import com.tugalsan.api.file.server.TS_FileUtils;
import static java.lang.System.out;
import java.nio.file.Path;
import java.util.Optional;

public class TS_FilePomPathUtils {

    public static Path pathMavenLoc = Path.of("C:\\Users\\tugal\\.m2\\repository");
    public static Path pathMavenGroupLoc = pathMavenLoc.resolve("com").resolve("tugalsan");
    public static String strGroupId = "com.tugalsan";
    public static Path pathProjectLoc = Path.of("C:\\me\\codes");
    public static Path pathProjectGroupLoc = pathProjectLoc.resolve(strGroupId);

    public static boolean deleteMavenGroupLoc() {
        return TS_DirectoryUtils.deleteDirectoryIfExists(pathMavenGroupLoc);
    }

    public static Optional<String> parseSubDir(String artifactId) {
        if (!artifactId.startsWith(strGroupId)) {
            out.println("WARNING skip artifact:" + artifactId);
            return Optional.empty();
        }
        var slice = artifactId.substring(strGroupId.length() + 1);
        var idx_dot = slice.indexOf(".");
        if (idx_dot == -1) {
            out.println("ERROR idx_dot == -1:" + artifactId);
            return Optional.empty();
        }
        return Optional.of(slice.substring(0, idx_dot));
    }

    public static Optional<Path> mavenLocByArtifactId(String artifactId) {
        var pom_xml = pathMavenGroupLoc.resolve(artifactId);
        if (!TS_FileUtils.isExistFile(pom_xml)) {
            out.println("ERRIR FILE NOT FOUND:" + pom_xml);
        }
        return Optional.of(pom_xml);
    }

    public static Optional<Path> projectPomByArtifactId(String artifactId) {
        var subDirName = parseSubDir(artifactId).orElse(null);
        if (subDirName == null) {
            return Optional.empty();
        }
        var pom_xml = pathProjectGroupLoc.resolve(subDirName).resolve(artifactId).resolve("pom.xml");
        if (!TS_FileUtils.isExistFile(pom_xml)) {
            out.println("ERRIR FILE NOT FOUND:" + pom_xml);
        }
        return Optional.of(pom_xml);
    }
}
