package com.thoughtworks.todolist.Exception;

public class LabelContentDuplicatedException extends  RuntimeException{
    public LabelContentDuplicatedException(String message){
        super(message);
    }
}
