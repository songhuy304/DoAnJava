package com.example.doanjava.repository;

import com.example.doanjava.entity.product;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository  extends PagingAndSortingRepository<product, Long>, JpaRepository<product, Long> {
    @Query("""
SELECT b FROM product b
WHERE b.title LIKE %?1%
OR b.category.namecategory LIKE %?1%
""")
    List<product> searchProduct(String keyword);

    List<product> searchProductByCategoryId(Long categoryId);
    default List<product> findAllBooks(Integer pageNo,
                                    Integer pageSize,
                                    String sortBy) {
        return findAll(PageRequest.of(pageNo,
                pageSize,
                Sort.by(sortBy)))

                .getContent();
    }


}
