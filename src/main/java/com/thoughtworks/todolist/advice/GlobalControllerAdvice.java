package com.thoughtworks.todolist.advice;

import com.thoughtworks.todolist.Exception.LabelContentDuplicatedException;
import com.thoughtworks.todolist.Exception.LabelNotFoundException;
import com.thoughtworks.todolist.Exception.TodoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class})
    public ErrorResponse handleBadRequest(IllegalArgumentException exception) {
        return new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.name());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({LabelNotFoundException.class})
    public ErrorResponse handleLabelNotFound(LabelNotFoundException exception) {
        return new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND.name());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({TodoNotFoundException.class})
    public ErrorResponse handleTodoNotFound(TodoNotFoundException exception) {
        return new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND.name());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({LabelContentDuplicatedException.class})
    public ErrorResponse handleLabelContentDuplicated(LabelContentDuplicatedException exception) {
        return new ErrorResponse(exception.getMessage(), HttpStatus.CONFLICT.name());
    }
}
