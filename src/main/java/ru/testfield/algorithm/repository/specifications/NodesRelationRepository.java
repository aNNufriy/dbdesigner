package ru.testfield.algorithm.repository.specifications;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.testfield.algorithm.model.bfap.Node;
import ru.testfield.algorithm.model.bfap.params.NodesRelation;

import java.util.UUID;

/**
 * Created by J.Bgood on 8/15/17.
 */
public interface NodesRelationRepository extends PagingAndSortingRepository<NodesRelation, UUID>, JpaSpecificationExecutor<Node> {
}
