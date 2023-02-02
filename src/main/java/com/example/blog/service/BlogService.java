package com.example.blog.service;

import com.example.blog.model.Blog;
import com.example.blog.repository.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class BlogService {
    @Autowired
    IBlogRepository iBlogRepository;

//    public List<Blog> getAll() {
//        return (List<Blog>) iBlogRepository.findAll();
//    }
public Page<Blog> getAll(Pageable pageable) {
    return  iBlogRepository.findAll(pageable);
}

    public Blog findBlogById(int id) {
        return iBlogRepository.findById(id).get();
    }

    public void create(Blog blog) {
        iBlogRepository.save(blog);
    }

    public void deleteById (int id){
        iBlogRepository.deleteById(id);
    }

}
