package classes.model;

import java.io.File;
import java.util.Date;

public class BookModel {
    private int bookId;
    private String bookTitle;
    private String description;
    private String author;
    private Date publishedDate;
    private String category;
    private String publisher;
    private String lastTakenPerson;
    private Date lastTakenDate;
    private File image;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getLastTakenPerson() {
        return lastTakenPerson;
    }

    public void setLastTakenPerson(String lastTakenPerson) {
        this.lastTakenPerson = lastTakenPerson;
    }

    public Date getLastTakenDate() {
        return lastTakenDate;
    }

    public void setLastTakenDate(Date lastTakenDate) {
        this.lastTakenDate = lastTakenDate;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }
}

