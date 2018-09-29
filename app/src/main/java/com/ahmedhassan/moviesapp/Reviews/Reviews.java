package com.ahmedhassan.moviesapp.Reviews;

/**
 * Created by Ahmed Hassan on 26/11/2016.
 */

public class Reviews {
    private String author;
    private String content;
    private String url;

    public Reviews() {

    }

    public Reviews( String author,String content) {
//        this.url = url;
        this.content = content;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
