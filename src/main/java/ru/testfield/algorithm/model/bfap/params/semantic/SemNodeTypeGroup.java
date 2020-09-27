package ru.testfield.algorithm.model.bfap.params.semantic;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
public class SemNodeTypeGroup {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Type(type="pg-uuid")
    private UUID id;

    private String name;

    @ManyToOne
    private SemNodeTypeGroup semNodeTypeGroup;

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

    public SemNodeTypeGroup getSemNodeTypeGroup() {
        return semNodeTypeGroup;
    }

    public void setSemNodeTypeGroup(SemNodeTypeGroup semNodeTypeGroup) {
        this.semNodeTypeGroup = semNodeTypeGroup;
    }
}
