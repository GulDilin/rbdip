package ru.itmo.rbdip.data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Task implements Serializable {



    Long id;
    String title;
    String description;
    Date deadline;
    private List<Tag> tags;

    public Task() {}

    @Override
    public String toString() {
        return title+'\n' +
                "\tdescription = " + description + "\n" +
                "\tdeadline = " + new SimpleDateFormat("dd.MM.yyyy").format(deadline) + "\n" +
                "\ttags = " + tags;
    }
}
