package ru.itmo.rbdip.data;

public class Tag {

    String title;

    public Tag(){}

    public Tag(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}
