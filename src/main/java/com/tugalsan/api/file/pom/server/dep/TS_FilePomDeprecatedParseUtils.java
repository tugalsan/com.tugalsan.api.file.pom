package com.tugalsan.api.file.pom.server.dep;

import com.tugalsan.api.file.server.TS_DirectoryUtils;
import com.tugalsan.api.file.xml.server.TS_FileXmlUtils;
import com.tugalsan.api.log.server.TS_Log;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TS_FilePomDeprecatedParseUtils {

//    final private static TS_Log d = TS_Log.of(false, TS_FilePomParseUtils.class);
//
//    public static Optional<String> groupId(Path prjPom) {
//        var doc = TS_FileXmlUtils.of(prjPom);
//        var root = TS_FileXmlUtils.getNodeRoot(doc);
//        return TS_FileXmlUtils.getChilderenNode(root, "groupId")
//                .map(node -> TS_FileXmlUtils.getText(node));
//    }
//
//    public static Optional<String> articactId(Path prjPom) {
//        var doc = TS_FileXmlUtils.of(prjPom);
//        var root = TS_FileXmlUtils.getNodeRoot(doc);
//        return TS_FileXmlUtils.getChilderenNode(root, "artifactId")
//                .map(node -> TS_FileXmlUtils.getText(node));
//    }
//
//    public static Optional<List<String>> depsThirdParty(Path prjPom) {
//        var doc = TS_FileXmlUtils.of(prjPom);
//        var root = TS_FileXmlUtils.getNodeRoot(doc);
//        var modules = TS_FileXmlUtils.getChilderenNode(root, "modules").orElse(null);
//        var depedencies = TS_FileXmlUtils.getChilderenNode(root, "dependencies").orElse(null);
//        if (depedencies == null && modules == null) {
//            d.ce("dep", "ERROR: prjPom not have dependencies nor modules node @ " + prjPom);
//            TS_FileXmlUtils.getChilderenLstExceptText(root).stream().forEach(node -> {
//                d.ce("dep", "node->" + node.getNodeName());
//            });
//            return Optional.empty();
//        }
//        var depCount = TS_FileXmlUtils.getChilderenLstExceptText(depedencies).stream()
//                .filter(node -> node.getNodeName().equals("dependency")).count();
//        d.ci("dep", "depCount", depCount);
//        List<String> deps_other = new ArrayList();
//        TS_FileXmlUtils.getChilderenLstExceptText(depedencies).stream()
//                .filter(node -> node.getNodeName().equals("dependency"))
//                .forEach(dependency -> {
//                    var groupId = TS_FileXmlUtils.getChilderenLstExceptText(dependency).stream()
//                            .filter(node -> node.getNodeName().equals("groupId"))
//                            .findAny().orElse(null);
//                    if (groupId == null) {
//                        d.ce("dep", "ERROR: dependency does not have groupId @ " + prjPom);
//                        TS_FileXmlUtils.getChilderenLstExceptText(dependency).stream().forEach(node -> {
//                            d.ce("dep", "node->" + node.getNodeName());
//                        });
//                        return;
//                    }
//                    var groupIdVal = TS_FileXmlUtils.getText(groupId);
//                    if (groupIdVal == null) {
//                        d.ce("dep", "ERROR: groupIdVal == null @ prjPom:" + prjPom);
//                        return;
//                    }
//                    if (groupIdVal.equals("${project.groupId}") || groupIdVal.equals("com.tugalsan")) {
////                        d.cr("dep", "skip groupIdVal", groupIdVal);
//                        return;
//                    }
//                    d.ci("dep", "groupIdVal", groupIdVal);
//                    var artifactId = TS_FileXmlUtils.getChilderenLstExceptText(dependency).stream()
//                            .filter(node -> node.getNodeName().equals("artifactId"))
//                            .findAny().orElse(null);
//                    if (artifactId == null) {
//                        d.ce("dep", "ERROR: groupId does not have artifactId @ groupId: " + groupIdVal + " @ :" + prjPom);
//                        TS_FileXmlUtils.getChilderenLstExceptText(dependency).stream().forEach(node -> {
//                            d.ce("dep", "node->" + node.getNodeName());
//                        });
//                        return;
//                    }
//                    var artifactIdVal = TS_FileXmlUtils.getText(artifactId);
//                    if (artifactIdVal == null) {
//                        d.ce("dep", "ERROR: artifactIdVal == null @ groupId: " + groupIdVal + " @ :" + prjPom);
//                        return;
//                    }
//                    d.ci("dep", "artifactIdVal", artifactIdVal);
//                    deps_other.add(artifactIdVal);
//                });
//        return Optional.of(deps_other);
//    }
//
//    public static Optional<List<TS_FilePomDependecy>> deps(Path prjPom) {
//        var doc = TS_FileXmlUtils.of(prjPom);
//        var root = TS_FileXmlUtils.getNodeRoot(doc);
//        var depedencies = TS_FileXmlUtils.getChilderenNode(root, "dependencies").orElse(null);
//        if (depedencies == null) {
//            d.ce("dep", "ERROR: prjPom not have dependencies node @ " + prjPom);
//            TS_FileXmlUtils.getChilderenLstExceptText(root).stream().forEach(node -> {
//                d.ce("dep", "node->" + node.getNodeName());
//            });
//            return Optional.empty();
//        }
//        var depCount = TS_FileXmlUtils.getChilderenLstExceptText(depedencies).stream()
//                .filter(node -> node.getNodeName().equals("dependency")).count();
//        d.ci("dep", "depCount", depCount);
//        List<String> deps_com_tugalsan = new ArrayList();
//        TS_FileXmlUtils.getChilderenLstExceptText(depedencies).stream()
//                .filter(node -> node.getNodeName().equals("dependency"))
//                .forEach(dependency -> {
//                    var groupId = TS_FileXmlUtils.getChilderenLstExceptText(dependency).stream()
//                            .filter(node -> node.getNodeName().equals("groupId"))
//                            .findAny().orElse(null);
//                    if (groupId == null) {
//                        d.ce("dep", "ERROR: dependency does not have groupId @ " + prjPom);
//                        TS_FileXmlUtils.getChilderenLstExceptText(dependency).stream().forEach(node -> {
//                            d.ce("dep", "node->" + node.getNodeName());
//                        });
//                        return;
//                    }
//                    var groupIdVal = TS_FileXmlUtils.getText(groupId);
//                    if (groupIdVal == null) {
//                        d.ce("dep", "ERROR: groupIdVal == null @ prjPom:" + prjPom);
//                        return;
//                    }
//                    if (groupIdVal.equals("com.tugalsan")) {
//                        d.ce("dep", "WARNING: com.tugalsan found in groupId. It should be ${project.groupId}", TS_DirectoryUtils.getName(prjPom.getParent()));
//                    }
//                    if (!(groupIdVal.equals("${project.groupId}") || groupIdVal.equals("com.tugalsan"))) {
////                        d.cr("dep", "skip groupIdVal", groupIdVal);
//                        return;
//                    }
//                    d.ci("dep", "groupIdVal", groupIdVal);
//                    var artifactId = TS_FileXmlUtils.getChilderenLstExceptText(dependency).stream()
//                            .filter(node -> node.getNodeName().equals("artifactId"))
//                            .findAny().orElse(null);
//                    if (artifactId == null) {
//                        d.ce("dep", "ERROR: groupId does not have artifactId @ groupId: " + groupIdVal + " @ :" + prjPom);
//                        TS_FileXmlUtils.getChilderenLstExceptText(dependency).stream().forEach(node -> {
//                            d.ce("dep", "node->" + node.getNodeName());
//                        });
//                        return;
//                    }
//                    var artifactIdVal = TS_FileXmlUtils.getText(artifactId);
//                    if (artifactIdVal == null) {
//                        d.ce("dep", "ERROR: artifactIdVal == null @ groupId: " + groupIdVal + " @ :" + prjPom);
//                        return;
//                    }
//                    d.ci("dep", "artifactIdVal", artifactIdVal);
//                    deps_com_tugalsan.add(artifactIdVal);
//                });
//        return Optional.of(deps_com_tugalsan);
//    }
}
