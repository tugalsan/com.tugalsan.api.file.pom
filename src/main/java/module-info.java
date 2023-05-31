module com.tugalsan.api.file.pom {
    requires java.xml;
    requires com.tugalsan.api.log;
    requires com.tugalsan.api.stream;
    requires com.tugalsan.api.file;
    requires com.tugalsan.api.tuple;
    requires com.tugalsan.api.file.xml;
    exports com.tugalsan.api.file.pom.server;
}