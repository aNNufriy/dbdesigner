package ru.testfield.algorithm.model;

import java.util.HashMap;
import java.util.Map;

public class ResultsContainer {
    private int counter;
    private Map data;

    public ResultsContainer() {
        this.data = new HashMap();
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }

    public int getCounter() {

        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
