package ru.testfield.algorithm.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.testfield.algorithm.model.bfap.params.Classifier;
import ru.testfield.algorithm.model.bfap.params.InnerType;
import ru.testfield.algorithm.model.bfap.params.semantic.SemValueType;
import ru.testfield.algorithm.repository.ClassifierRepository;
import ru.testfield.algorithm.repository.semantic.SemValueTypeRepository;

import java.util.List;

/**
 * Created by J.Bgood on 11/29/17.
 */
@RestController
@RequestMapping("/rest")
public class RestSemanticController {

    @Autowired
    private ClassifierRepository classifierRepository;

    @Autowired
    private SemValueTypeRepository semValueTypeRepository;

    @RequestMapping("/classifiers")
    public Iterable<Classifier> classifiers(){
        return classifierRepository.findAll();
    }

    @RequestMapping("/valuetypes")
    public Iterable<SemValueType> semValueTypes(){
        return semValueTypeRepository.findAll();
    }

}
