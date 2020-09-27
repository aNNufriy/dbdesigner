package ru.testfield.algorithm.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.testfield.algorithm.model.bfap.*;

import java.util.List;
import java.util.UUID;

/**
 * Created by J.Bgood on 8/15/17.
 */
public interface NodeRepository extends PagingAndSortingRepository<Node, UUID>, JpaSpecificationExecutor<Node> {
    List<Node> findBySemNodeType_Id(UUID id);
}