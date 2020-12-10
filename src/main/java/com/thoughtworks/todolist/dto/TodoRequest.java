package com.thoughtworks.todolist.dto;

public class TodoRequest {
    private String content;

    public TodoRequest(String content) {
        this.content = content;
    }

    public TodoRequest() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
