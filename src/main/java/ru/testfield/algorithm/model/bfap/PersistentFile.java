package ru.testfield.algorithm.model.bfap;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by J.Bgood on 12/6/17.
 */
@Entity
@Table(name = "files")
public class PersistentFile implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Type(type="pg-uuid")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "path", columnDefinition = "TEXT")
    private String path;

    @Column(name = "size")
    private Long size;

    @Transient
    private UUID semValueTypeId;

    private String md5Hash;

    public String getMd5Hash() {
        return md5Hash;
    }

    public void setMd5Hash(String md5Hash) {
        this.md5Hash = md5Hash;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public UUID getSemValueTypeId() {
        return semValueTypeId;
    }

    public void setSemValueTypeId(UUID semValueTypeId) {
        this.semValueTypeId = semValueTypeId;
    }
}