package ru.testfield.algorithm.repository.semantic;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.testfield.algorithm.model.bfap.params.semantic.SemNodeTypesRelation;

import java.util.List;
import java.util.UUID;

/**
 * Created by J.Bgood on 8/15/17.
 */
public interface SemNodeTypesRelationRepository extends PagingAndSortingRepository<SemNodeTypesRelation, UUID>, JpaSpecificationExecutor<SemNodeTypesRelation> {

    @Query("select sntr from SemNodeTypesRelation sntr where sntr.semNodeTypeLeft.id = ?1 or sntr.semNodeTypeLeft.id = ?1")
    List<SemNodeTypesRelation> findBySemNodeType(UUID semNodeType);

}
