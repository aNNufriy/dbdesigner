package ru.testfield.algorithm.model.bfap.params.semantic;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import ru.testfield.algorithm.model.bfap.params.NodesRelation;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
public class SemNodeTypesRelation implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Type(type="pg-uuid")
    private UUID id;

    @NotNull
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private SemNodeType semNodeTypeLeft;

    @NotNull
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private SemNodeType semNodeTypeRight;

    @NotNull
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private SemRelation semRelation;

    @OneToMany(mappedBy = "semNodeTypesRelation", cascade = CascadeType.ALL)
    private Set<NodesRelation> nodesRelation;

    public Set<NodesRelation> getNodesRelation() {
        return nodesRelation;
    }

    public void setNodesRelation(Set<NodesRelation> nodesRelation) {
        this.nodesRelation = nodesRelation;
    }

    public SemNodeType getSemNodeTypeRight() {
        return semNodeTypeRight;
    }

    public void setSemNodeTypeRight(SemNodeType semNodeTypeRight) {
        this.semNodeTypeRight = semNodeTypeRight;
    }

    public SemRelation getSemRelation() {
        return semRelation;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setSemRelation(SemRelation semRelation) {
        this.semRelation = semRelation;
    }

    public SemNodeType getSemNodeTypeLeft() {

        return semNodeTypeLeft;
    }

    public void setSemNodeTypeLeft(SemNodeType semNodeTypeLeft) {
        this.semNodeTypeLeft = semNodeTypeLeft;
    }
}
