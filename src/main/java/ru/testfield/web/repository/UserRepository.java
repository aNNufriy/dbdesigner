package ru.testfield.web.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.testfield.web.model.cms.User;

import java.util.UUID;

/**
 * Created by J.Bgood on 8/15/17.
 */
public interface UserRepository extends PagingAndSortingRepository<User, UUID> {
    User findByUsername(String username);
}
