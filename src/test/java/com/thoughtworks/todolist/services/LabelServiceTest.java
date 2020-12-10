package com.thoughtworks.todolist.services;

import com.thoughtworks.todolist.models.Label;
import com.thoughtworks.todolist.repositories.LabelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class LabelServiceTest {
    @InjectMocks
    private LabelService labelService;
    @Mock
    LabelRepository labelRepository;

    @Test
    public void should_return_all_labels_when_get_all_given_all_labels(){
        //given
        final List<Label> expected = Arrays.asList(
                new Label("label1","#ffffff"),
                new Label("label2","#ffffff")
        );
        when(labelRepository.findAll()).thenReturn(expected);

        //when
        final List<Label> labels = labelService.getAll();

        //then
        assertEquals(expected, labels);
    }
}
