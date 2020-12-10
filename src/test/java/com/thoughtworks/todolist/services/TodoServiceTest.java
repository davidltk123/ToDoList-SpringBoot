package com.thoughtworks.todolist.services;

import com.thoughtworks.todolist.Exception.TodoNotFoundException;
import com.thoughtworks.todolist.models.Label;
import com.thoughtworks.todolist.models.Todo;
import com.thoughtworks.todolist.repositories.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {
    @InjectMocks
    private TodoService todoService;
    @Mock
    TodoRepository todoRepository;

    @Test
    public void should_return_all_todos_when_get_all_given_all_todos(){
        //given
        final List<Todo> expected = Arrays.asList(
                new Todo("todo1"),
                new Todo("todo2")
        );
        when(todoRepository.findAll()).thenReturn(expected);

        //when
        final List<Todo> todos = todoService.getAll();


        //then
        assertEquals(expected, todos);
    }

    @Test
    public void should_return_specific_todo_when_get_specific_todo_given_valid_todo_id(){
        //given
        final Todo expected = new Todo("todo1");
        when(todoRepository.findById("1")).thenReturn(Optional.of(expected));

        //when
        final Todo todo = todoService.getById("1");

        //then
        assertEquals(expected, todo);
    }

    @Test
    public void should_return_todo_not_found_when_get_specific_todo_given_invalid_todo_id(){
        //given
        when(todoRepository.findById(any())).thenReturn(Optional.empty());

        //when
        TodoNotFoundException todoNotFoundException = assertThrows(TodoNotFoundException.class, () -> {
            todoService.getById("99999");
        });

        //then
        assertEquals("Todo Not Found!", todoNotFoundException.getMessage());
    }

}
