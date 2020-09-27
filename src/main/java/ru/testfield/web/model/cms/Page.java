package ru.testfield.web.model.cms;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;

@Entity
@Table(name="cms_pages")
public class Page implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Type(type="pg-uuid")
    private UUID id;
    
    @Column(name="title")
    private String title;
    
    @Column(name="body")
    private String body;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="author")
    private User author;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
