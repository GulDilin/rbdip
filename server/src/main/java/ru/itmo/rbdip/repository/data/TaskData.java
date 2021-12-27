package ru.itmo.rbdip.repository.data;

import java.util.Date;
import java.util.List;

public class TaskData {

    public TaskData(String title, String description, Date deadline, List<String> tags) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.tags = tags;
    }

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
