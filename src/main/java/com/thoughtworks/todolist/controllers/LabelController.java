package com.thoughtworks.todolist.controllers;

import com.thoughtworks.todolist.dto.LabelRequest;
import com.thoughtworks.todolist.dto.LabelResponse;
import com.thoughtworks.todolist.mappers.LabelMapper;
import com.thoughtworks.todolist.services.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/labels")
public class LabelController {
    @Autowired
    private LabelService labelService;
    @Autowired
    private LabelMapper labelMapper;

    @GetMapping()
    public List<LabelResponse> getAll() {
        return labelService.getAll().stream().map(labelMapper::toResponse).collect(Collectors.toList());
    }

    @GetMapping("/{labelId}")
    public LabelResponse getById(@PathVariable String labelId) {
        return labelMapper.toResponse(labelService.getById(labelId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LabelResponse create(@RequestBody LabelRequest labelRequest) {
        return labelMapper.toResponse(labelService.save(labelMapper.toEntity(labelRequest)));
    }

    @PutMapping("/{labelId}")
    public LabelResponse update(@PathVariable String labelId, @RequestBody LabelRequest labelRequest) {
        return labelMapper.toResponse(labelService.update(labelId, labelMapper.toEntity(labelRequest)));
    }

    @DeleteMapping("/{labelId}")
    public void delete(@PathVariable String labelId) {
        labelService.delete(labelId);
    }


}
