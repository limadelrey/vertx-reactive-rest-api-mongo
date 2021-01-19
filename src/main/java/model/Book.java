package model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.vertx.core.json.JsonObject;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book {

    private String _id;
    private String author;
    private String country;
    private String imageLink;
    private String language;
    private String link;
    private Integer pages;
    private String title;
    private Integer year;

    public Book() {
    }

    public Book(JsonObject jsonObject) {
        this._id = jsonObject.getString("_id");
        this.author = jsonObject.getString("author");
        this.country = jsonObject.getString("country");
        this.imageLink = jsonObject.getString("imageLink");
        this.language = jsonObject.getString("language");
        this.link = jsonObject.getString("link");
        this.pages = jsonObject.getInteger("pages");
        this.title = jsonObject.getString("title");
        this.year = jsonObject.getInteger("year");
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

}
