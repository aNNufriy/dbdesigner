package ru.testfield.algorithm.model.bfap.params.semantic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import ru.testfield.algorithm.model.bfap.Node;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name="sem_node_type")
public class SemNodeType {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Type(type="pg-uuid")
    private UUID id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "semNodeType",orphanRemoval = true)
    @JsonIgnore
    private Set<Node> nodes = new HashSet<>();

    @ManyToMany
    @JsonIgnore
    private Set<SemValueTypeGroup> semValueTypeGroups = new HashSet<>();

    @OneToMany(mappedBy = "semNodeTypeLeft",orphanRemoval = true)
    @JsonIgnore
    private Set<SemNodeTypesRelation> leftSemNodeTypesRelation = new HashSet<>();

    @OneToMany(mappedBy = "semNodeTypeRight",orphanRemoval = true)
    @JsonIgnore
    private Set<SemNodeTypesRelation> rightSemNodeTypesRelation = new HashSet<>();

    @OneToMany(mappedBy = "parent")
    @JsonIgnore
    private Set<SemNodeType> children = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private SemNodeType parent;

    @PreRemove
    public void preRemove(){
        for(SemNodeType child: children){
            child.setParent(null);
        }
    }

    public Set<SemNodeType> getChildren() {
        return children;
    }

    public void setChildren(Set<SemNodeType> children) {
        this.children = children;
    }

    public Set<SemNodeTypesRelation> getLeftSemNodeTypesRelation() {
        return leftSemNodeTypesRelation;
    }

    public void setLeftSemNodeTypesRelation(Set<SemNodeTypesRelation> leftSemNodeTypesRelation) {
        this.leftSemNodeTypesRelation = leftSemNodeTypesRelation;
    }

    public Set<SemNodeTypesRelation> getRightSemNodeTypesRelation() {
        return rightSemNodeTypesRelation;
    }

    public void setRightSemNodeTypesRelation(Set<SemNodeTypesRelation> rightSemNodeTypesRelation) {
        this.rightSemNodeTypesRelation = rightSemNodeTypesRelation;
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

    public Set<SemValueTypeGroup> getSemValueTypeGroups() {
        return semValueTypeGroups;
    }

    public void setSemValueTypeGroups(Set<SemValueTypeGroup> semValueTypeGroups) {
        this.semValueTypeGroups = semValueTypeGroups;
    }

    public SemNodeType getParent() {
        return parent;
    }

    public void setParent(SemNodeType parent) {
        this.parent = parent;
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SemNodeType that = (SemNodeType) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
