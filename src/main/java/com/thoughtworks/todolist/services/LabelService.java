package com.thoughtworks.todolist.services;

import com.thoughtworks.todolist.Exception.LabelContentDuplicatedException;
import com.thoughtworks.todolist.Exception.LabelNotFoundException;
import com.thoughtworks.todolist.models.Label;
import com.thoughtworks.todolist.repositories.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelService {
    @Autowired
    private LabelRepository labelRepository;

    public List<Label> getAll() {
        return labelRepository.findAll();
    }

    public Label getById(String id) {
        return labelRepository.findById(id).orElseThrow(() -> new LabelNotFoundException("Label Not Found!"));
    }

    public Label save(Label label) {
        if (getLabelByContent(label.getContent()) == null) {
            return labelRepository.save(label);
        }
        throw new LabelContentDuplicatedException("Label already exists!");
    }

    public Label update(String id, Label labelUpdate) {
        Label label = getById(id);
        Label existLabel = getLabelByContent(labelUpdate.getContent());
        if (existLabel == null || label.getId().equals(existLabel.getId())) {
            labelUpdate.setId(label.getId());
            return labelRepository.save(labelUpdate);
        }
        throw new LabelContentDuplicatedException("Label already exists!");
    }

    private Label getLabelByContent(String content){
        return labelRepository.findByContent(content);
    }

    public void delete(String id) {
        Label label = getById(id);
        labelRepository.deleteById(label.getId());
    }
}
