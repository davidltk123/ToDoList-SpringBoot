package com.thoughtworks.todolist.dto;

import java.util.ArrayList;
import java.util.List;

public class TodoRequest {
    private String content;
    private boolean complete;
    private List<String> labelIds;

    public TodoRequest(String content, boolean complete, List<String> labelIds) {
        this.content = content;
        this.complete = complete;
        this.labelIds = labelIds;
    }

    public TodoRequest() {
        this.labelIds = new ArrayList<>();
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

    public List<String> getLabelIds() {
        return labelIds;
    }

    public void setLabelIds(List<String> labelIds) {
        this.labelIds = labelIds;
    }
}
