package bookStore.studyExample.bookManager.domain;

import javax.persistence.*;

//POJO-таблица
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;
    private String author;
    private String genre;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User author_signature;

    private String filename;

    public Book() {
    }

    public String getAuthor_SignatureName(){
        return author_signature !=null ? author_signature.getUsername() : "<none>";
    }

    public Book(String title, String author, String genre, User user) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.author_signature = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public User getAuthor_signature() {
        return author_signature;
    }

    public void setAuthor_signature(User author_signature) {
        this.author_signature = author_signature;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}