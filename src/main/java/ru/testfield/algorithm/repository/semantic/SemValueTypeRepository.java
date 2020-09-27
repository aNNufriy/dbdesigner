package ru.testfield.algorithm.repository.semantic;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.testfield.algorithm.model.bfap.params.InnerType;
import ru.testfield.algorithm.model.bfap.params.semantic.SemValueType;

import java.util.List;
import java.util.UUID;

/**
 * Created by J.Bgood on 8/15/17.
 */
public interface SemValueTypeRepository extends PagingAndSortingRepository<SemValueType, UUID>, JpaSpecificationExecutor<SemValueType> {
    List<SemValueType> findByInnerType(InnerType innerType);
    List<SemValueType> findByInnerTypeNot(InnerType innerType);
}