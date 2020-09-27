package ru.testfield;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import ru.testfield.algorithm.repository.NodeRepository;
import ru.testfield.algorithm.repository.semantic.SemNodeTypeRepository;
import ru.testfield.algorithm.repository.semantic.SemNodeTypesRelationRepository;
import ru.testfield.algorithm.repository.semantic.SemRelationRepository;
import ru.testfield.algorithm.repository.specifications.NodesRelationRepository;

import javax.transaction.Transactional;

@SpringBootApplication
public class Application implements ApplicationListener<ApplicationReadyEvent> {

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

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
    @Transactional
	public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
		logger.info("Application up and running");
	}
}
