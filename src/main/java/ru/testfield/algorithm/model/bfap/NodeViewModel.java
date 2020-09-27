package ru.testfield.algorithm.model.bfap;

import ru.testfield.algorithm.model.bfap.params.Value;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by J.Bgood on 12/6/17.
 */
public class NodeViewModel {

    private UUID id;

    private String num;

    private String name;

    private String function;

    private String consumer;

    private String okr;

    private Date createdDate;

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    public String getOkr() {
        return okr;
    }

    public void setOkr(String okr) {
        this.okr = okr;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public static List<NodeViewModel> mapNodes(List<Node> nodes){
        List<NodeViewModel> nodeViewModels = new ArrayList<NodeViewModel>();
        if(nodes != null){
            for (Node node : nodes) {
                NodeViewModel nodeViewModel = new NodeViewModel();
                nodeViewModel.setId(node.getId());
                node.getValues();
                for(Value value: node.getValues()){
                    if(value.getSemValueType().getId().toString().equals("a6b6ca81-5478-425b-b91c-2c07c6a4b811")){
                        nodeViewModel.setNum(value.getLongValue().toString());
                    }
                    if(value.getSemValueType().getId().toString().equals("d8a14fba-7081-45f1-95d6-8e1eed1eddba")){
                        nodeViewModel.setName(value.getStringValue());
                    }
                    if(value.getSemValueType().getId().toString().equals("c66d672d-1604-4399-9986-6c95bc377dd1")){
                        nodeViewModel.setFunction(value.getStringValue());
                    }
                    if(value.getSemValueType().getId().toString().equals("76f334a1-d2ca-497c-a4d1-688c6ab041c7")){
                        nodeViewModel.setConsumer(value.getStringValue());
                    }
                    if(value.getSemValueType().getId().toString().equals("03759ca4-dc3f-41de-b8b5-e3a4371664cd")){
                        nodeViewModel.setOkr(value.getStringValue());
                    }
                }
                nodeViewModels.add(nodeViewModel);
            }
        }
        return nodeViewModels;
    }
}