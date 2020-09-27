package ru.testfield.algorithm.model.bfap;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import ru.testfield.algorithm.model.bfap.params.NodesRelation;
import ru.testfield.algorithm.model.bfap.params.semantic.SemNodeType;
import ru.testfield.algorithm.model.bfap.params.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

/**
 * Created by J.Bgood on 12/6/17.
 */
@Entity
@Table(name = "node")
public class Node implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Type(type="pg-uuid")
    private UUID id;

    private String name;

    @OneToMany(mappedBy = "node",cascade = CascadeType.ALL,orphanRemoval=true)
    private Set<Value> values = new HashSet<>();

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "sem_node_type_id", nullable = false)
    @NotNull
    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    private SemNodeType semNodeType;

    @Column(name="add_time", updatable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date addTime;

    @Column(name="edit_time")
    private Date editTime;

    @OneToMany(mappedBy = "nodeLeft", orphanRemoval=true)
    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    private Set<NodesRelation> leftRelation;

    @OneToMany(mappedBy = "nodeRight", orphanRemoval=true)
    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    private Set<NodesRelation> rightRelation;

    public Set<NodesRelation> getLeftRelation() {
        return leftRelation;
    }

    public void setLeftRelation(Set<NodesRelation> leftRelation) {
        this.leftRelation = leftRelation;
    }

    public Set<NodesRelation> getRightRelation() {
        return rightRelation;
    }

    public void setRightRelation(Set<NodesRelation> rightRelation) {
        this.rightRelation = rightRelation;
    }

    public Set<Value> getValues() {
        return values;
    }

    public void setValues(Set<Value> values) {
        this.values = values;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public SemNodeType getSemNodeType() {
        return semNodeType;
    }

    public void setSemNodeType(SemNodeType semNodeType) {
        this.semNodeType = semNodeType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}