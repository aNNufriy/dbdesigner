package ru.testfield.algorithm.repository.semantic;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.testfield.algorithm.model.bfap.params.semantic.SemRelation;

import java.util.UUID;

/**
 * Created by J.Bgood on 8/15/17.
 */
public interface SemRelationRepository extends PagingAndSortingRepository<SemRelation, UUID>, JpaSpecificationExecutor<SemRelation> {
}
