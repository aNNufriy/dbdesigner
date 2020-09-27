package ru.testfield.algorithm.model.request;

import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

public class NodeFilter extends ModelSearchQuery {

    @NotNull
    private FilterType filterType;

    private List<String> values = new LinkedList<>();

    private String rangeStart;

    private String rangeEnd;

    public FilterType getFilterType() {
        return filterType;
    }

    public void setFilterType(FilterType filterType) {
        this.filterType = filterType;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public String getRangeStart() {
        return rangeStart;
    }

    public void setRangeStart(String rangeStart) {
        this.rangeStart = rangeStart;
    }

    public String getRangeEnd() {
        return rangeEnd;
    }

    public void setRangeEnd(String rangeEnd) {
        this.rangeEnd = rangeEnd;
    }
}
