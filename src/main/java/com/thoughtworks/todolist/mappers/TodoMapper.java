package com.thoughtworks.todolist.mappers;

import com.thoughtworks.todolist.dto.LabelResponse;
import com.thoughtworks.todolist.dto.TodoRequest;
import com.thoughtworks.todolist.dto.TodoResponse;
import com.thoughtworks.todolist.models.Label;
import com.thoughtworks.todolist.models.Todo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TodoMapper {
    public Todo toEntity(TodoRequest todoRequest) {
        Todo todo = new Todo();
        BeanUtils.copyProperties(todoRequest, todo);
        return todo;
    }

    public TodoResponse toResponse(Todo todo, List<Label> labels) {
        TodoResponse todoResponse = new TodoResponse();
        BeanUtils.copyProperties(todo, todoResponse);
        todoResponse.setLabels(labels);
        return todoResponse;
    }
}
