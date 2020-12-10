package com.thoughtworks.todolist.Exception;


public class LabelNotFoundException extends RuntimeException {
    public LabelNotFoundException(String message){
        super(message);
    }
}
