package com.example.andresarango.memeit.modello;

/**
 * Created by leighdouglas on 1/21/17.
 */

public class MemeURI {

    private Long _id;
    private String uri;

    public MemeURI() {
        this.uri = "no uri";
    }

    public MemeURI(String uri) {
        this.uri = uri;
    }

    public Long get_id() {
        return _id;
    }

    public String getUri() {
        return uri;
    }
}
