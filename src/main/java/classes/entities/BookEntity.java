package classes.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "book", schema = "nbds_library", catalog = "")
public class BookEntity {
    private int bookId;
    private String bookTitle;
    private String description;
    private String author;
    private Date publishedDate;
    private String category;
    private String publisher;
    private String lastTakenPerson;
    private Date lastTakenDate;
    private String image;

    public BookEntity() {
    }

    public BookEntity(String bookTitle, String description, String author, Date publishedDate, String category, String publisher, String imageName) {
        this.bookTitle = bookTitle;
        this.description = description;
        this.author = author;
        this.publishedDate = publishedDate;
        this.category = category;
        this.publisher = publisher;
        this.image = imageName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOOK_ID")
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "BOOK_TITLE")
    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "AUTHOR")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "PUBLISHED_DATE")
    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookEntity that = (BookEntity) o;

        if (bookId != that.bookId) return false;
        if (bookTitle != null ? !bookTitle.equals(that.bookTitle) : that.bookTitle != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (publishedDate != null ? !publishedDate.equals(that.publishedDate) : that.publishedDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bookId;
        result = 31 * result + (bookTitle != null ? bookTitle.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (publishedDate != null ? publishedDate.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "CATEGORY")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Basic
    @Column(name = "PUBLISHER")
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Basic
    @Column(name = "LAST_TAKEN_PERSON")
    public String getLastTakenPerson() {
        return lastTakenPerson;
    }

    public void setLastTakenPerson(String lastTakenPerson) {
        this.lastTakenPerson = lastTakenPerson;
    }

    @Basic
    @Column(name = "LAST_TAKEN_DATE")
    public Date getLastTakenDate() {
        return lastTakenDate;
    }

    public void setLastTakenDate(Date lastTakenDate) {
        this.lastTakenDate = lastTakenDate;
    }

    @Basic
    @Column(name = "IMAGE")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
