package ru.itmo.rbdip.repository.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskData {
    String title;
    String description;
    Date deadline;
    private List<String> tags;
}
