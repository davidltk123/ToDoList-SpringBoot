package com.thoughtworks.todolist.services;

import com.thoughtworks.todolist.Exception.TodoNotFoundException;
import com.thoughtworks.todolist.models.Label;
import com.thoughtworks.todolist.models.Todo;
import com.thoughtworks.todolist.repositories.LabelRepository;
import com.thoughtworks.todolist.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private LabelRepository labelRepository;

    public List<Todo> getAll() {
        return todoRepository.findAll();
    }

    public Todo getById(String id) {
        return todoRepository.findById(id).orElseThrow(()-> new TodoNotFoundException("Todo Not Found!"));
    }

    public Todo create(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo update(String id, Todo updatedTodo) {
        Todo todo = getById(id);
        updatedTodo.setId(todo.getId());
        return todoRepository.save(updatedTodo);
    }

    public List<Label> getLabels(String id) {
        Todo todo = getById(id);
        List<Label> labels = new ArrayList<>();
        labelRepository.findAllById(todo.getLabelIds()).forEach(labels::add);
        return labels;
    }

    public void delete(String id) {
        Todo todo = getById(id);
        todoRepository.deleteById(todo.getId());
    }
}
