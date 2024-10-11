package eu.skymines.lib.sql;

public enum SQLType {

    SQLITE("jdbc:sqlite:"),
    MYSQL("jdbc:mysql://");

    private String url;

    SQLType(String url) {
        this.url = url;
    }
}
