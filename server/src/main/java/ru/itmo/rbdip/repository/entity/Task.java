package ru.itmo.rbdip.repository.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, unique = true)
    String title;
    @Column(nullable = false)
    String description;
    @Column(nullable = false, columnDefinition = "date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date deadline;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "task_tags",
            joinColumns =
            @JoinColumn(name = "task_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "tag_id", referencedColumnName = "id", columnDefinition = "bigint NOT NULL constraint tag_id_fk references tag on delete cascade")
    )
    private List<Tag> tags;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User user;


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
