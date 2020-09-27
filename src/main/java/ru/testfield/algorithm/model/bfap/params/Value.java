package ru.testfield.algorithm.model.bfap.params;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import ru.testfield.algorithm.model.bfap.Node;
import ru.testfield.algorithm.model.bfap.params.semantic.SemValueType;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "value")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Value {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Type(type="pg-uuid")
    private UUID id;

    private Long longValue;
    private Double doubleValue;
    private String stringValue;
    private UUID uuidValue;
    private Date dateValue;

    @ManyToOne
    private SemValueType semValueType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Node node;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public SemValueType getSemValueType() {
        return semValueType;
    }

    public void setSemValueType(SemValueType semValueType) {
        this.semValueType = semValueType;
    }

    public Long getLongValue() {
        return longValue;
    }

    public void setLongValue(Long longValue) {
        this.longValue = longValue;
    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public Date getDateValue() {
        return dateValue;
    }

    public void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }

    public UUID getUuidValue() {
        return uuidValue;
    }

    public void setUuidValue(UUID uuidValue) {
        this.uuidValue = uuidValue;
    }

    @Override
    public String toString(){
        switch(semValueType.getInnerType()){
            case DOUBLE:    return doubleValue.toString();
            case LONG:      return longValue.toString();
            case STRING:    return stringValue;
            case DATETIME:  return dateValue.toString();
            case CLASSIFIER:return longValue.toString();
            case FILE:      return uuidValue.toString();
            default:        return "NOT AVAILABLE";
        }
    }
}