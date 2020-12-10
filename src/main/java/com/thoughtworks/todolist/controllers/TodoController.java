package com.thoughtworks.todolist.controllers;

import com.thoughtworks.todolist.dto.TodoRequest;
import com.thoughtworks.todolist.dto.TodoResponse;
import com.thoughtworks.todolist.mappers.LabelMapper;
import com.thoughtworks.todolist.mappers.TodoMapper;
import com.thoughtworks.todolist.models.Label;
import com.thoughtworks.todolist.models.Todo;
import com.thoughtworks.todolist.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
        return todoService.getAll().stream().map(this::getTodoResponse).collect(Collectors.toList());
    }

    private TodoResponse getTodoResponse(Todo todo){
        List<Label> labels = todoService.getLabels(todo.getId());
        return todoMapper.toResponse(todo,labels);
    }

    @GetMapping("/{todoId}")
    public TodoResponse getById(@PathVariable String todoId){
        return getTodoResponse(todoService.getById(todoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse create(@RequestBody TodoRequest todoRequest){
        return getTodoResponse(todoService.create(todoMapper.toEntity(todoRequest)));
    }

    @PutMapping("/{todoId}")
    public TodoResponse update(@PathVariable String todoId, @RequestBody TodoRequest todoRequest){
        return getTodoResponse(todoService.update(todoId, todoMapper.toEntity(todoRequest)));
    }

    @DeleteMapping("/{todoId}")
    public void delete(@PathVariable String todoId){
        todoService.delete(todoId);
    }
}
