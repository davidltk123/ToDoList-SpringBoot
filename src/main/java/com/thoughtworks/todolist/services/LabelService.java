package com.thoughtworks.todolist.services;

import com.thoughtworks.todolist.models.Label;
import com.thoughtworks.todolist.repositories.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelService {
    @Autowired
    private LabelRepository labelRepository;

    public List<Label> getAll(){
        return labelRepository.findAll();
    }
}
