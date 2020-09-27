package ru.testfield.algorithm.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.testfield.algorithm.model.bfap.params.Classifier;
import ru.testfield.algorithm.model.bfap.params.ClassifierValue;

import java.util.List;
import java.util.UUID;

/**
 * Created by J.Bgood on 8/15/17.
 */
public interface ClassifierValueRepository extends PagingAndSortingRepository<ClassifierValue, UUID> {
    List<ClassifierValue> findByClassifierOrderByCode(Classifier classifier);
    @Transactional
    long deleteByClassifier_Id(UUID classifierId);
}
