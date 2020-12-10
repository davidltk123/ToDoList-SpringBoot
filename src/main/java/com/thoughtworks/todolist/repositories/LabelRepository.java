package com.thoughtworks.todolist.repositories;

import com.thoughtworks.todolist.models.Label;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LabelRepository extends MongoRepository<Label, String> {
}
