package ru.testfield.algorithm.service;

import org.springframework.transaction.annotation.Transactional;
import ru.testfield.algorithm.model.bfap.Node;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by J.Bgood on 12/15/17.
 */
public interface NodeService {

    @Transactional
    void mapValues(Node node, HttpServletRequest request);

}
