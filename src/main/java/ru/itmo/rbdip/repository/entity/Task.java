package ru.itmo.rbdip.repository.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Task {

    public Task(Long id, String title, String description, Date deadline, List<Tag> tags) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.tags = tags;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, unique = true)
    String title;
    @Column(nullable = false)
    String description;
    @Column(nullable = false, columnDefinition = "date")
    Date deadline;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="task_tags",
            joinColumns=
            @JoinColumn(name="task_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="tag_id", referencedColumnName="id", columnDefinition = "bigint NOT NULL constraint tag_id_fk references tag on delete cascade")
    )
    private List<Tag> tags;

    public Task() {

    }
}
