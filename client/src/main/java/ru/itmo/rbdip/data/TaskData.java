package ru.itmo.rbdip.data;

import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
public class TaskData {

    String title;
    String description;
    Date deadline;
    private List<String> tags;
}
