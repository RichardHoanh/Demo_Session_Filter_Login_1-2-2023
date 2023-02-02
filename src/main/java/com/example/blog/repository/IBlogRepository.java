package com.example.blog.repository;

import com.example.blog.model.Blog;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IBlogRepository extends PagingAndSortingRepository<Blog,Integer> {

}
