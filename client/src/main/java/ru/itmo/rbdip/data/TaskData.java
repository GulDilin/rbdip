package ru.itmo.rbdip.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
public class TaskData {

    public TaskData(){

    }

    String title;
    String description;
    Date deadline;
    private List<String> tags;

    public List<String> getTags() {
        return tags;
    }

    public String getTitle() {
        return title;
    }

    public Date getDeadline() {
        return deadline;
    }

    public String getDescription() {
        return description;
    }
}
