package com.example.blog.service;


import com.example.blog.model.Category;
import com.example.blog.repository.ICategotyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CategoryService {
    @Autowired
    ICategotyRepository iCategotyRepository;
    public List<Category> getAll(){
        return (List<Category>) iCategotyRepository.findAll();
    }



    public Category findById(int id) {
        return iCategotyRepository.findById(id).get();
    }








}
