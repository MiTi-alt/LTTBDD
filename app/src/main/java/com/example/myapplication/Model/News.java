package com.example.myapplication.Model;

public class News {
    private int id;
    private String title;
    private String abstracts;
    private String author;
    private String image;
    private String dateTIme;
    private String url;
    private String timeUpdate;

    public News(int id, String title, String abstracts, String author, String image, String dateTIme, String url, String timeUpdate) {
        this.id = id;
        this.title = title;
        this.abstracts = abstracts;
        this.author = author;
        this.image = image;
        this.dateTIme = dateTIme;
        this.url = url;
        this.timeUpdate = timeUpdate;
    }

    public News() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDateTIme() {
        return dateTIme;
    }

    public void setDateTIme(String dateTIme) {
        this.dateTIme = dateTIme;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTimeUpdate() {
        return timeUpdate;
    }

    public void setTimeUpdate(String timeUpdate) {
        this.timeUpdate = timeUpdate;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", abstracts='" + abstracts + '\'' +
                ", author='" + author + '\'' +
                ", image='" + image + '\'' +
                ", dateTIme='" + dateTIme + '\'' +
                ", url='" + url + '\'' +
                ", timeUpdate='" + timeUpdate + '\'' +
                '}';
    }
}
