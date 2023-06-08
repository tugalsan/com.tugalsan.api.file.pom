package com.tugalsan.api.file.pom.server;

import com.tugalsan.api.file.server.TS_FileUtils;
import com.tugalsan.api.log.server.TS_Log;
import com.tugalsan.api.stream.client.TGS_StreamUtils;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TS_FilePom {

    final private static TS_Log d = TS_Log.of(TS_FilePom.class);

    private TS_FilePom(String error) {
        isLoadedSuccessfully = false;
        this.error = error;
    }
    final public String error;

    private TS_FilePom(Path pom_xml, List<TS_FilePom> useDepBuffer) {
        this.pom_xml = pom_xml;
        this.dependenciesFull = useDepBuffer;
        if (!TS_FileUtils.isExistFile(pom_xml)) {
            error = "ERROR: pom_xml not exists @ " + pom_xml;
            d.ce("dep", error);
            isLoadedSuccessfully = false;
            return;
        }
        this.articactId = TS_FilePomParseUtils.articactId(pom_xml).orElse(null);
        if (articactId == null) {
            error = "ERROR: articactId == null @ " + pom_xml;
            d.ce("dep", error);
            isLoadedSuccessfully = false;
            return;
        }
        this.groupId = TS_FilePomParseUtils.groupId(pom_xml).orElse(null);
        if (groupId == null) {
            error = "ERROR: groupId == null @ " + pom_xml;
            d.ce("dep", error);
            isLoadedSuccessfully = false;
            return;
        }
        var dependenciesStr = TS_FilePomParseUtils.deps(pom_xml).orElse(null);
        if (dependenciesStr == null) {
            error = "ERROR: dependenciesStr == null @ " + pom_xml;
            d.ce("dep", error);
            isLoadedSuccessfully = false;
            return;
        }
        dependenciesThirdParty = TS_FilePomParseUtils.depsThirdParty(pom_xml).orElse(null);
        if (dependenciesThirdParty == null) {
            error = "ERROR: dependenciesStr == null @ " + pom_xml;
            d.ce("dep", error);
            isLoadedSuccessfully = false;
            return;
        }
        dependencies = TGS_StreamUtils.toLst(dependenciesStr.stream().map(s -> TS_FilePom.of(s).orElse(new TS_FilePom(s))));
        dependenciesFull = new ArrayList();
        dependenciesThirdPartyFull = new ArrayList();
        fillDepFullFrom(this);
        error = null;
        isLoadedSuccessfully = true;
    }

    final public boolean isLoadedSuccessfully;
    public Path pom_xml;
    public String articactId;
    public String groupId;
    public List<TS_FilePom> dependencies;
    public List<String> dependenciesThirdParty;
    public List<String> dependenciesThirdPartyFull;
    public List<TS_FilePom> dependenciesFull;

    private void fillDepFullFrom(TS_FilePom pom) {
        pom.dependenciesThirdParty.stream().forEach(dep -> {
            if (dependenciesThirdPartyFull.stream().filter(df -> df.equals(dep)).findAny().isPresent()) {
                return;
            }
            dependenciesThirdPartyFull.add(dep);
        });
        pom.dependencies.stream().forEach(dep -> {
            if (dependenciesFull.stream().filter(df -> df.equals(dep)).findAny().isPresent()) {
                return;
            }
            dependenciesFull.add(dep);
            fillDepFullFrom(dep);
        });
    }

    public static TS_FilePom of(Path pom_xml) {
        return new TS_FilePom(pom_xml, null);
    }

    public static TS_FilePom of(Path pom_xml, List<TS_FilePom> useDepBuffer) {
        if (useDepBuffer == null) {
            useDepBuffer = new ArrayList();
        }
        return new TS_FilePom(pom_xml, useDepBuffer);
    }

    public static Optional<TS_FilePom> of(String artifactId) {
        var o = TS_FilePomPathUtils.projectPomByArtifactId(artifactId).orElse(null);
        if (o == null) {
            return Optional.empty();
        }
        return Optional.of(TS_FilePom.of(o));
    }

    @Override
    public String toString() {
        return d.className + "{" + "isLoadedSuccessfully=" + isLoadedSuccessfully + ", pom_xml=" + pom_xml + ", articactId=" + articactId + ", groupId=" + groupId + ", dependencies=" + dependencies + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(articactId, groupId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TS_FilePom other = (TS_FilePom) obj;
        if (!Objects.equals(this.articactId, other.articactId)) {
            return false;
        }
        return Objects.equals(this.groupId, other.groupId);
    }

}
