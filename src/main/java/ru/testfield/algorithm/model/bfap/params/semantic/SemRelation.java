package ru.testfield.algorithm.model.bfap.params.semantic;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
public class SemRelation {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Type(type="pg-uuid")
    private UUID id;

    private String name;

    private Boolean bidirectional = false;

    @OneToMany(mappedBy = "semRelation", cascade = CascadeType.ALL)
    private Set<SemNodeTypesRelation> semNodeTypesRelations;

    public Set<SemNodeTypesRelation> getSemNodeTypesRelations() {
        return semNodeTypesRelations;
    }

    public void setSemNodeTypesRelations(Set<SemNodeTypesRelation> semNodeTypesRelations) {
        this.semNodeTypesRelations = semNodeTypesRelations;
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

    public Boolean getBidirectional() {
        return bidirectional;
    }

    public void setBidirectional(Boolean bidirectional) {
        this.bidirectional = bidirectional;
    }
}
