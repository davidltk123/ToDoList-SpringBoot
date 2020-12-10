package com.thoughtworks.todolist.dto;

import com.thoughtworks.todolist.models.Label;

import java.util.List;

public class TodoResponse {
    private String id;
    private String content;
    private boolean complete;
    private List<Label> labels;

    public TodoResponse(String id, String content, boolean complete, List<Label> labels) {
        this.id = id;
        this.content = content;
        this.complete = complete;
        this.labels = labels;
    }

    public TodoResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }
}
