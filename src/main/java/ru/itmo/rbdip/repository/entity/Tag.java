package ru.itmo.rbdip.repository.entity;


import javax.persistence.*;
import java.util.List;

@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, unique = true)
    String title;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="task_tags",
            joinColumns=
            @JoinColumn(name="tag_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="task_id", referencedColumnName="id", columnDefinition = "bigint NOT NULL constraint task_id_fk references task on delete cascade")
    )
    private List<Task> tasks;

    public Tag(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Tag() {

    }
}
