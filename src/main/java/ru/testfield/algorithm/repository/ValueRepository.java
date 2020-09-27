package ru.testfield.algorithm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.testfield.algorithm.model.bfap.params.Value;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by J.Bgood on 12/16/17.
 */
public interface ValueRepository extends PagingAndSortingRepository<Value, UUID> {

    @Transactional
    Long deleteByUuidValue(UUID uuidValue);

    @Transactional
    long deleteBySemValueType_Id(UUID semValueTypeId);

    Set<Value> findByNode_IdAndSemValueType_Id(UUID nodeId, UUID semValueTypeId);
}
