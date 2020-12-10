package com.thoughtworks.todolist.controllers;

import com.thoughtworks.todolist.dto.LabelResponse;
import com.thoughtworks.todolist.mappers.LabelMapper;
import com.thoughtworks.todolist.services.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/labels")
public class LabelController {
    @Autowired
    private LabelService labelService;
    @Autowired
    private LabelMapper labelMapper;

    @GetMapping()
    public List<LabelResponse> getAll(){
        return labelService.getAll().stream().map(labelMapper::toResponse).collect(Collectors.toList());
    }

}
