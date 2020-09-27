package ru.testfield.algorithm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.testfield.algorithm.model.bfap.Node;
import ru.testfield.algorithm.model.bfap.params.Value;
import ru.testfield.algorithm.model.request.NodeFilter;
import ru.testfield.algorithm.repository.NodeRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class FilteringService {

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private EntityManager em;

    public List<Node> filter(@RequestBody(required = false) final NodeFilter[] filters) {
        final List<Node> all = nodeRepository.findAll(
                new Specification<Node>() {
                    @Override
                    public Predicate toPredicate(Root<Node> nodeRoot, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                        EntityType<Value> Value_ = em.getMetamodel().entity(Value.class);
                        EntityType<Node> Node_ = em.getMetamodel().entity(Node.class);
                        criteriaQuery.distinct(true);
                        List<Predicate> andPredicates = new LinkedList<>();
                        if(filters!=null && filters.length!=0) {
                            for (NodeFilter filterRequest : filters) {
                                List<Predicate> orPredicates = new LinkedList<>();
                                switch (filterRequest.getFilterType()) {
                                    case NODE_FILTER:
                                        for (String filterValue : filterRequest.getValues()) {
                                            UUID filterUUIDValue = UUID.fromString(filterValue);
                                            Predicate predicate = cb.or(cb.equal(nodeRoot.<UUID>get("id"), filterUUIDValue));
                                            orPredicates.add(predicate);
                                        }
                                        if (orPredicates.size() > 0) {
                                            andPredicates.add(cb.or(orPredicates.toArray(new Predicate[orPredicates.size()])));
                                        }
                                        break;
                                    case NODE_TYPE_FILTER:
                                        for (String filterValue : filterRequest.getValues()) {
                                            UUID filterUUIDValue = UUID.fromString(filterValue);
                                            Predicate predicate = cb.or(
                                                    cb.equal(
                                                            nodeRoot.get("semNodeType").get("id"),
                                                            filterUUIDValue
                                                    )
                                            );
                                            orPredicates.add(predicate);
                                        }
                                        if (orPredicates.size() > 0) {
                                            andPredicates.add(cb.or(orPredicates.toArray(new Predicate[orPredicates.size()])));
                                        }
                                        break;
                                    case VALUE_FILTER:
                                        SetJoin<Node, Value> join = nodeRoot.join(Node_.getSet("values", Value.class));

                                        Path stringValue = join.get(Value_.getSingularAttribute("stringValue",String.class));
                                        andPredicates.add(cb.like(stringValue, "%"+filterRequest.getValues().get(0)+"%"));

                                        break;
                                }
                            }
                            if (andPredicates.size() == 0) {
                                return cb.conjunction();
                            } else {
                                return cb.and(andPredicates.toArray(new Predicate[andPredicates.size()]));
                            }
                        }else{
                            return cb.conjunction();
                        }
                    }
                }
        );
        return all;
    }
}