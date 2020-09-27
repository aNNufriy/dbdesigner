package ru.testfield.algorithm.model.bfap.params;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import ru.testfield.algorithm.model.bfap.Node;
import ru.testfield.algorithm.model.bfap.params.semantic.SemNodeTypesRelation;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class NodesRelation implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Type(type="pg-uuid")
    private UUID id;

    @ManyToOne
    private Node nodeLeft;

    @ManyToOne
    private Node nodeRight;

    @ManyToOne
    private SemNodeTypesRelation semNodeTypesRelation;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Node getNodeLeft() {
        return nodeLeft;
    }

    public void setNodeLeft(Node nodeLeft) {
        this.nodeLeft = nodeLeft;
    }

    public Node getNodeRight() {
        return nodeRight;
    }

    public void setNodeRight(Node nodeRight) {
        this.nodeRight = nodeRight;
    }

    public SemNodeTypesRelation getSemNodeTypesRelation() {
        return semNodeTypesRelation;
    }

    public void setSemNodeTypesRelation(SemNodeTypesRelation semNodeTypesRelation) {
        this.semNodeTypesRelation = semNodeTypesRelation;
    }
}