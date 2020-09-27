package ru.testfield.algorithm.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.testfield.algorithm.model.bfap.Node;
import ru.testfield.algorithm.model.request.NodeFilter;
import ru.testfield.algorithm.service.FilteringService;

import java.util.List;

@RestController
@RequestMapping("/rest/filter")
public class FilteringController {

    @Autowired
    private FilteringService filteringService;

    @ResponseBody
    @RequestMapping("")
    public List<Node> filter(@RequestBody(required = false) final NodeFilter[] filters) {
        return filteringService.filter(filters);
    }

}
