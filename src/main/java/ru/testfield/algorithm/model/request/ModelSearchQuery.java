package ru.testfield.algorithm.model.request;

/**
 * Created by J.Bgood on 12/10/17.
 */
public class ModelSearchQuery {
    private String text;
    private Long modelCharacter;
    private Long commandLevel;
    private Long activityStage;
    private Long modelingActionForm;
    private Long modelType;
    private Long hardwarePlatform;
    private Long softwarePlatform;

    public Long getModelingActionForm() {
        return modelingActionForm;
    }

    public void setModelingActionForm(Long modelingActionForm) {
        this.modelingActionForm = modelingActionForm;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getCommandLevel() {
        return commandLevel;
    }

    public void setCommandLevel(Long commandLevel) {
        this.commandLevel = commandLevel;
    }

    public Long getActivityStage() {
        return activityStage;
    }

    public void setActivityStage(Long activityStage) {
        this.activityStage = activityStage;
    }

    public Long getModelCharacter() {
        return modelCharacter;
    }

    public void setModelCharacter(Long modelCharacter) {
        this.modelCharacter = modelCharacter;
    }

    public Long getModelType() {
        return modelType;
    }

    public void setModelType(Long modelType) {
        this.modelType = modelType;
    }

    public Long getHardwarePlatform() {
        return hardwarePlatform;
    }

    public void setHardwarePlatform(Long hardwarePlatform) {
        this.hardwarePlatform = hardwarePlatform;
    }

    public Long getSoftwarePlatform() {
        return softwarePlatform;
    }

    public void setSoftwarePlatform(Long softwarePlatform) {
        this.softwarePlatform = softwarePlatform;
    }
}
