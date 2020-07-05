package com.example.exercice5;

public class Listenouveaute {
    private String title;
    private String body;
    private String author_fullname;
    private String image;
    private String kind;
    private String name;
    public Listenouveaute() {
    }
    public Listenouveaute(String kind, String body,String title, String author, String image,String name) {
        this.title = title;
        this.body = body;
        this.author_fullname = author;
        this.image = image;
        this.kind=kind;
        this.name=name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAuthor() {
        return author_fullname;
    }

    public void setAuthor(String author) {
        this.author_fullname = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
