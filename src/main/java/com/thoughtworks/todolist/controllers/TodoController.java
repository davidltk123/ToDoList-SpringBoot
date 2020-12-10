package com.thoughtworks.todolist.controllers;

import com.thoughtworks.todolist.dto.TodoResponse;
import com.thoughtworks.todolist.mappers.TodoMapper;
import com.thoughtworks.todolist.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;
    @Autowired
    private TodoMapper todoMapper;

    @GetMapping
    public List<TodoResponse> getAll(){
        return todoService.getAll().stream().map(todoMapper::toResponse).collect(Collectors.toList());
    }

}
