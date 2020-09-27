package ru.testfield.algorithm.model.bfap.params.semantic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SortNatural;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="sem_value_type_group")
public class SemValueTypeGroup {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Type(type="pg-uuid")
    private UUID id;

    private String name;

    @OneToMany(mappedBy = "semValueTypeGroup", cascade = CascadeType.ALL)
    @JsonIgnore
    @OrderBy("ordr")
    private List<SemValueType> semValueTypes = new ArrayList<>();

    @ManyToMany(mappedBy = "semValueTypeGroups", cascade = CascadeType.ALL)
    private List<SemNodeType> semNodeTypes = new ArrayList<>();

    @PreRemove
    public void preRemove(){
        for (SemNodeType semNodeType : semNodeTypes) {
            semNodeType.getSemValueTypeGroups().remove(this);
        }
    }

    @PreUpdate
    protected void removeOrphanMaps(){
        System.out.println("hello");
    }

    public List<SemValueType> getSemValueTypes() {
        return semValueTypes;
    }

    public void setSemValueTypes(List<SemValueType> semValueTypes) {
        this.semValueTypes = semValueTypes;
    }

    public List<SemNodeType> getSemNodeTypes() {
        return semNodeTypes;
    }

    public void setSemNodeTypes(List<SemNodeType> semNodeTypes) {
        this.semNodeTypes = semNodeTypes;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SemValueTypeGroup that = (SemValueTypeGroup) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
