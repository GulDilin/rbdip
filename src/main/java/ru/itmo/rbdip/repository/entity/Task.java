package ru.itmo.rbdip.repository.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Task implements Serializable {

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

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name="task_tags",
            joinColumns=
            @JoinColumn(name="task_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="tag_id", referencedColumnName="id", columnDefinition = "bigint NOT NULL constraint tag_id_fk references tag on delete cascade")
    )
    private List<Tag> tags;

    public Task() {}

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", tags=" + tags +
                '}';
    }
}
