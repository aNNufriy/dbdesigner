package ru.testfield.algorithm.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.testfield.algorithm.model.bfap.PersistentFile;

import java.util.UUID;

/**
 * Created by J.Bgood on 8/15/17.
 */
public interface FileRepository extends PagingAndSortingRepository<PersistentFile, UUID> {
}
