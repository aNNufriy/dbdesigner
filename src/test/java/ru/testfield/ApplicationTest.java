package ru.testfield;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.assertj.core.util.Lists;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.testfield.algorithm.model.bfap.Node;
import ru.testfield.algorithm.model.bfap.params.semantic.SemNodeType;
import ru.testfield.algorithm.model.bfap.params.semantic.SemNodeTypesRelation;
import ru.testfield.algorithm.model.bfap.params.semantic.SemRelation;
import ru.testfield.algorithm.repository.NodeRepository;
import ru.testfield.algorithm.repository.semantic.SemNodeTypeRepository;
import ru.testfield.algorithm.repository.semantic.SemNodeTypesRelationRepository;
import ru.testfield.algorithm.repository.semantic.SemRelationRepository;
import ru.testfield.algorithm.repository.specifications.NodesRelationRepository;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApplicationTest {

    protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	SemNodeTypeRepository semNodeTypeRepository;

	@Autowired
    SemRelationRepository semRelationRepository;

	@Autowired
    SemNodeTypesRelationRepository semNodeTypesRelationRepository;

	@Autowired
    NodeRepository nodeRepository;

	@Autowired
    NodesRelationRepository nodesRelationRepository;

    @Test
    @Transactional
    public void test1_creatingSemantics() {

        SemNodeType semNodeType1 = new SemNodeType();
        semNodeType1.setName("type 1 node");
        semNodeType1 = semNodeTypeRepository.save(semNodeType1);

        SemNodeType semNodeType2 = new SemNodeType();
        semNodeType2.setName("type 2 node");
        semNodeType2 = semNodeTypeRepository.save(semNodeType2);

        SemRelation semRelation = new SemRelation();
        semRelation.setName("relation between node types");

        SemNodeTypesRelation sntr = new SemNodeTypesRelation();
        sntr.setSemNodeTypeLeft(semNodeType1);
        sntr.setSemNodeTypeRight(semNodeType2);
        sntr.setSemRelation(semRelation);

        semNodeTypesRelationRepository.save(sntr);

        logger.info("test1_creatingSemantics test passed");

        Node node1 = new Node();
        Node node2 = new Node();

        node1.setName("name1");
        node1.setSemNodeType(semNodeType1);
        node2.setName("name2");
        node2.setSemNodeType(semNodeType2);

        nodeRepository.save(node1);
        nodeRepository.save(node2);

        logger.info("test2_creatingData test passed");
    }

}
