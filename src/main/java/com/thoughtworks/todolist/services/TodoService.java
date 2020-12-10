package com.thoughtworks.todolist.services;

import com.thoughtworks.todolist.Exception.TodoNotFoundException;
import com.thoughtworks.todolist.models.Todo;
import com.thoughtworks.todolist.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAll() {
        return todoRepository.findAll();
    }

    public Todo getById(String id) {
        return todoRepository.findById(id).orElseThrow(()-> new TodoNotFoundException("Todo Not Found!"));
    }

    public Todo create(Todo todo) {
        return todoRepository.save(todo);
    }
}
