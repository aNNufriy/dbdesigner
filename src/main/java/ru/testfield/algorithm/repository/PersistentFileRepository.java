package ru.testfield.algorithm.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.testfield.algorithm.model.bfap.PersistentFile;

import java.util.UUID;

/**
 * Created by J.Bgood on 12/16/17.
 */
public interface PersistentFileRepository extends PagingAndSortingRepository<PersistentFile, UUID> {
}