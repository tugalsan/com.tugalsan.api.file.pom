package com.tugalsan.api.file.pom.server;

import com.tugalsan.api.file.server.TS_FileUtils;
import com.tugalsan.api.log.server.TS_Log;
import com.tugalsan.api.stream.client.TGS_StreamUtils;
import java.nio.file.Path;
import java.util.List;

public class TS_FilePom {

    final private static TS_Log d = TS_Log.of(TS_FilePom.class);

    private TS_FilePom(Path pom_xml) {
        this.pom_xml = pom_xml;
        if (!TS_FileUtils.isExistFile(pom_xml)) {
            d.ce("dep", "ERROR: pom_xml not exists @ " + pom_xml);
            isLoadedSuccessfully = false;
            return;
        }
        this.articactId = TS_FilePomParseUtils.articactId(pom_xml).orElse(null);
        if (articactId == null) {
            d.ce("dep", "ERROR: articactId == null @ " + pom_xml);
            isLoadedSuccessfully = false;
            return;
        }
        this.groupId = TS_FilePomParseUtils.groupId(pom_xml).orElse(null);
        if (groupId == null) {
            d.ce("dep", "ERROR: groupId == null @ " + pom_xml);
            isLoadedSuccessfully = false;
            return;
        }
        var dependenciesStr = TS_FilePomParseUtils.deps(pom_xml).orElse(null);
        if (dependenciesStr == null) {
            d.ce("dep", "ERROR: dependenciesStr == null @ " + pom_xml);
            isLoadedSuccessfully = false;
            return;
        }
        dependencies = TGS_StreamUtils.toLst(dependenciesStr.stream().map(s -> TS_FilePom.of(articactId)));
        isLoadedSuccessfully = true;
    }

    final public boolean isLoadedSuccessfully;
    public Path pom_xml;
    public String articactId;
    public String groupId;
    public List<TS_FilePom> dependencies;

    public static TS_FilePom of(Path pom_xml) {
        return new TS_FilePom(pom_xml);
    }

    public static TS_FilePom of(String artifactId) {
        return new TS_FilePom(TS_FilePomPathUtils.ofArtifactId(artifactId).orElse(null));
    }

    @Override
    public String toString() {
        return d.className + "{" + "isLoadedSuccessfully=" + isLoadedSuccessfully + ", pom_xml=" + pom_xml + ", articactId=" + articactId + ", groupId=" + groupId + ", dependencies=" + dependencies + '}';
    }

}
