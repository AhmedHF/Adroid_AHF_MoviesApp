package com.ahmedhassan.moviesapp.Trailers;

/**
 * Created by Ahmed Hassan on 25/11/2016.
 */

public class Trailers {
    private String key;
   private String name;

    public Trailers() {
    }

    public Trailers(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
