package com.thoughtworks.todolist.repositories;

import com.thoughtworks.todolist.models.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodoRepository extends MongoRepository<Todo, String> {
}
