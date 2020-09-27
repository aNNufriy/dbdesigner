package ru.testfield.algorithm.model.request;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class ModelSearchFilter {

    private String text;
    private Set<Integer> modelCharacter;
    private Set<Integer> commandLevel;
    private Set<Integer> activityStage;
    private Set<Integer> modelingActionForm;
    private Set<Integer> modelType;
    private Set<Integer> hardwarePlatform;
    private Set<Integer> softwarePlatform;
    private Set<Integer> process;
    private Set<Integer> detalization;
    private Set<Integer> environmentCondition;
    private Set<Integer> modelingControl;
    private Set<Integer> calculationsOnData;

    @Override
    public ModelSearchFilter clone(){
        ModelSearchFilter newone = new ModelSearchFilter();

        Field[] declaredFields = ModelSearchFilter.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if(declaredField.getType() == Set.class){
                try {
                    declaredField.setAccessible(true);
                    Set<Integer> keySet = (Set<Integer>) declaredField.get(this);
                    if (keySet != null && keySet.size() != 0) {
                        Set<Integer> tmpKeySet = new HashSet<>();
                        tmpKeySet.addAll(keySet);
                        declaredField.set(newone,tmpKeySet);
                    }
                    declaredField.setAccessible(false);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return newone;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Set<Integer> getModelCharacter() {
        return modelCharacter;
    }

    public void setModelCharacter(Set<Integer> modelCharacter) {
        this.modelCharacter = modelCharacter;
    }

    public Set<Integer> getCommandLevel() {
        return commandLevel;
    }

    public void setCommandLevel(Set<Integer> commandLevel) {
        this.commandLevel = commandLevel;
    }

    public Set<Integer> getActivityStage() {
        return activityStage;
    }

    public void setActivityStage(Set<Integer> activityStage) {
        this.activityStage = activityStage;
    }

    public Set<Integer> getModelingActionForm() {
        return modelingActionForm;
    }

    public void setModelingActionForm(Set<Integer> modelingActionForm) {
        this.modelingActionForm = modelingActionForm;
    }

    public Set<Integer> getModelType() {
        return modelType;
    }

    public void setModelType(Set<Integer> modelType) {
        this.modelType = modelType;
    }

    public Set<Integer> getHardwarePlatform() {
        return hardwarePlatform;
    }

    public void setHardwarePlatform(Set<Integer> hardwarePlatform) {
        this.hardwarePlatform = hardwarePlatform;
    }

    public Set<Integer> getSoftwarePlatform() {
        return softwarePlatform;
    }

    public void setSoftwarePlatform(Set<Integer> softwarePlatform) {
        this.softwarePlatform = softwarePlatform;
    }

    public Set<Integer> getProcess() {
        return process;
    }

    public void setProcess(Set<Integer> process) {
        this.process = process;
    }

    public Set<Integer> getDetalization() {
        return detalization;
    }

    public void setDetalization(Set<Integer> detalization) {
        this.detalization = detalization;
    }

    public Set<Integer> getEnvironmentCondition() {
        return environmentCondition;
    }

    public void setEnvironmentCondition(Set<Integer> environmentCondition) {
        this.environmentCondition = environmentCondition;
    }

    public Set<Integer> getModelingControl() {
        return modelingControl;
    }

    public void setModelingControl(Set<Integer> modelingControl) {
        this.modelingControl = modelingControl;
    }

    public Set<Integer> getCalculationsOnData() {
        return calculationsOnData;
    }

    public void setCalculationsOnData(Set<Integer> calculationsOnData) {
        this.calculationsOnData = calculationsOnData;
    }
}
