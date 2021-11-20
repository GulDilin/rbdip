package ru.itmo.rbdip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.rbdip.repository.entity.Tag;
import ru.itmo.rbdip.repository.entity.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    public List<Task> findAllByTagsOrderByDeadlineAsc(List<Tag> tags);
}
