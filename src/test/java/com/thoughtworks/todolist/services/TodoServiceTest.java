package com.thoughtworks.todolist.services;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        final List<Label> labels = todoService.getAll();


        //then
        assertEquals(expected, labels);
    }
}
