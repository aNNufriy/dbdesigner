package ru.testfield.algorithm.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.testfield.algorithm.model.bfap.params.Value;
import ru.testfield.algorithm.repository.ValueRepository;

import java.util.UUID;

/**
 * Created by J.Bgood on 11/29/17.
 */
@RestController
@RequestMapping("/rest")
public class RestValuesController {

    @Autowired
    private ValueRepository valueRepository;

    @RequestMapping("/value/{valueId}")
    public Value classifiers(@PathVariable("valueId") UUID valueId){
        return valueRepository.findOne(valueId);
    }

}
