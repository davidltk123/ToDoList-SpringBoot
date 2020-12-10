package com.thoughtworks.todolist.services;

import com.thoughtworks.todolist.Exception.LabelContentDuplicatedException;
import com.thoughtworks.todolist.Exception.LabelNotFoundException;
import com.thoughtworks.todolist.models.Label;
import com.thoughtworks.todolist.repositories.LabelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class LabelServiceTest {
    @InjectMocks
    private LabelService labelService;
    @Mock
    LabelRepository labelRepository;

    @Test
    public void should_return_all_labels_when_get_all_given_all_labels() {
        //given
        final List<Label> expected = Arrays.asList(
                new Label("label1", "#ffffff"),
                new Label("label2", "#ffffff")
        );
        when(labelRepository.findAll()).thenReturn(expected);

        //when
        final List<Label> labels = labelService.getAll();

        //then
        assertEquals(expected, labels);
    }

    @Test
    public void should_return_specific_label_when_get_specific_label_given_valid_id() {
        //given
        final Label expected = new Label("label1", "#ffffff");
        when(labelRepository.findById("1")).thenReturn(Optional.of(expected));

        //when
        final Label label = labelService.getById("1");

        //then
        assertEquals(expected, label);
    }

    @Test
    public void should_throw_label_not_found_when_get_specific_label_given_invalid_id() {
        //given
        when(labelRepository.findById(any())).thenReturn(Optional.empty());

        //when
        LabelNotFoundException labelNotFoundException = assertThrows(LabelNotFoundException.class, () -> {
            labelService.getById("99999");
        });

        //then
        assertEquals("Label Not Found!", labelNotFoundException.getMessage());
    }

    @Test
    public void should_return_created_label_when_create_label_given_no_label_in_database() {
        //given
        final Label expected = new Label("label1", "#ffffff");
        when(labelRepository.save(expected)).thenReturn(expected);

        //when
        labelService.save(expected);
        ArgumentCaptor<Label> labelArgumentCaptor = ArgumentCaptor.forClass(Label.class);
        verify(labelRepository, times(1)).save(labelArgumentCaptor.capture());

        //then
        final Label label = labelArgumentCaptor.getValue();
        assertEquals(expected, label);
    }

    @Test
    public void should_throw_content_duplicated_when_create_label_given_label_content_already_exists_in_database() {
        //given
        final Label label = new Label("label1", "#ffffff");
        when(labelRepository.findByContent(any())).thenReturn(label);

        //when
        LabelContentDuplicatedException labelContentDuplicatedException = assertThrows(LabelContentDuplicatedException.class, () -> {
            labelService.save(label);
        });

        //then
        assertEquals("Label already exists!", labelContentDuplicatedException.getMessage());
    }

    @Test
    public void should_return_updated_label_when_update_label_given_valid_label_id() {
        //given
        final Label originalLabel = new Label("label1", "#ffffff");
        final Label updatedLabel = new Label("label2", "#ffffff");
        when(labelRepository.findById("1")).thenReturn(Optional.of(originalLabel));
        when(labelRepository.save(updatedLabel)).thenReturn(updatedLabel);

        //when
        final Label label = labelService.update("1", updatedLabel);

        //then
        assertEquals(updatedLabel, label);
    }

    @Test
    public void should_throw_label_not_found_when_update_label_given_invalid_id() {
        //given
        when(labelRepository.findById(any())).thenReturn(Optional.empty());

        //when
        LabelNotFoundException labelNotFoundException = assertThrows(LabelNotFoundException.class, () -> {
            labelService.getById("99999");
        });

        //then
        assertEquals("Label Not Found!", labelNotFoundException.getMessage());
    }

    @Test
    public void should_throw_content_duplicated_when_update_label_given_label_content_already_exists_in_database() {
        //given
        final Label label = new Label("label1", "#ffffff");
        when(labelRepository.findByContent(any())).thenReturn(label);

        //when
        LabelContentDuplicatedException labelContentDuplicatedException = assertThrows(LabelContentDuplicatedException.class, () -> {
            labelService.update("1", label);
        });

        //then
        assertEquals("Label already exists!", labelContentDuplicatedException.getMessage());
    }

    @Test
    public void should_delete_specific_label_when_delete_given_valid_label_id() {
        //given
        final Label expected = new Label("label1", "#ffffff");
        when(labelRepository.findById("1")).thenReturn(Optional.of(expected));

        //when
        labelService.delete("1");

        //then
        verify(labelRepository, times(1)).deleteById(expected.getId());
    }

    @Test
    public void should_throw_label_not_found_exception_when_delete_given_invalid_label_id() {
        //given
        final Label label = new Label("label1", "#ffffff");
        when(labelRepository.findById(any())).thenReturn(Optional.empty());

        //when
        LabelNotFoundException labelNotFoundException = assertThrows(LabelNotFoundException.class, () -> {
            labelService.delete("99999");
        });

        //then
        assertEquals("Label Not Found!", labelNotFoundException.getMessage());
    }


}
