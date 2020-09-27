package ru.testfield.algorithm.model.bfap.params;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import ru.testfield.algorithm.model.bfap.params.semantic.SemValueType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
public class Classifier {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Type(type="pg-uuid")
    private UUID id;

    @Column(columnDefinition = "TEXT")
    private String name;

    @OneToMany(mappedBy = "classifier", cascade = CascadeType.ALL)
    private Set<ClassifierValue> classifierValues = new HashSet<>();

    @OneToMany(mappedBy = "classifier", cascade = CascadeType.ALL)
    private Set<SemValueType> semValueTypes = new HashSet<>();

    public void setId(UUID id) {
        this.id = id;
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

    public Set<ClassifierValue> getClassifierValues() {
        return classifierValues;
    }

    public void setClassifierValues(Set<ClassifierValue> classifierValues) {
        this.classifierValues = classifierValues;
    }

    public Set<SemValueType> getSemValueTypes() {
        return semValueTypes;
    }

    public void setSemValueTypes(Set<SemValueType> semValueTypes) {
        this.semValueTypes = semValueTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classifier that = (Classifier) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
