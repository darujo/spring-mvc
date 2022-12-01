package ru.darujo.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.darujo.model.Product;

import java.util.List;

@Repository
@Primary
public interface ProductRepository extends PagingAndSortingRepository<Product,Long> {
    List<Product> findByPriceBetween(double min, double max );

    default Iterable<Product> findPage(int page, int size) {
        return findAll(PageRequest.of(page,size, Sort.by("title")));
    }
}
