package com.edgardleal.graph;

public class PathNotFoundException extends Exception {
    public PathNotFoundException(String format) {
        super(format);
    }

    public PathNotFoundException() {
    }
}
