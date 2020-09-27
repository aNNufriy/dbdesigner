package ru.testfield.web.repository.cms;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.testfield.web.model.cms.Page;

import java.util.UUID;

/**
 * Created by J.Bgood on 8/15/17.
 */
public interface PageRepository extends PagingAndSortingRepository<Page, UUID> {
}
