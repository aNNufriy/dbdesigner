package ru.testfield.algorithm.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.testfield.algorithm.model.bfap.params.ClassifierValue;
import ru.testfield.algorithm.repository.ClassifierValueRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("admin/data/classifier_value")
public class ClassifierValueController {

    @Autowired
    private ClassifierValueRepository classifierValueRepository;

    @ResponseBody
    @RequestMapping(value = "{id}/delete")
    public Map<String,Object> delete(@PathVariable("id") UUID id) {
        classifierValueRepository.delete(id);
        Map<String,Object> result = new HashMap<>();
        result.put("success",true);
        result.put("id",id);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Map<String,Object> edit(ClassifierValue classifierValue) {
        classifierValueRepository.save(classifierValue);
        Map<String,Object> result = new HashMap<>();
        result.put("classifierValue",classifierValue);
        result.put("success",true);
        return result;
    }

}
