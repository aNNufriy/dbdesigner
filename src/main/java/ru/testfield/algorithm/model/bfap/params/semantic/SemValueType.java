package ru.testfield.algorithm.model.bfap.params.semantic;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import ru.testfield.algorithm.model.bfap.params.Classifier;
import ru.testfield.algorithm.model.bfap.params.InnerType;
import ru.testfield.algorithm.model.bfap.params.Value;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="sem_value_type")
public class SemValueType {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Type(type="pg-uuid")
    private UUID id;

    private String name;

    @Enumerated(EnumType.STRING)
    private InnerType innerType;

    @ManyToOne
    private SemValueTypeGroup semValueTypeGroup;

    private Boolean singleValue = false;

    private Boolean required = false;

    @OneToMany(mappedBy = "semValueType", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Value> values = new HashSet<>();

    @ManyToOne
    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Classifier classifier;

    private Long ordr;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SemValueType that = (SemValueType) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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

    public InnerType getInnerType() {
        return innerType;
    }

    public void setInnerType(InnerType innerType) {
        this.innerType = innerType;
    }

    public SemValueTypeGroup getSemValueTypeGroup() {
        return semValueTypeGroup;
    }

    public void setSemValueTypeGroup(SemValueTypeGroup semValueTypeGroup) {
        this.semValueTypeGroup = semValueTypeGroup;
    }

    public Boolean getSingleValue() {
        return singleValue;
    }

    public void setSingleValue(Boolean singleValue) {
        this.singleValue = singleValue;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Set<Value> getValues() {
        return values;
    }

    public void setValues(Set<Value> values) {
        this.values = values;
    }

    public Classifier getClassifier() {
        return classifier;
    }

    public void setClassifier(Classifier classifier) {
        this.classifier = classifier;
    }

    public Long getOrdr() {
        return ordr;
    }

    public void setOrdr(Long ordr) {
        this.ordr = ordr;
    }
}
