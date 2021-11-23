package ru.itmo.rbdip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itmo.rbdip.repository.entity.Task;

import java.util.List;

@Repository

public interface TaskRepository extends JpaRepository<Task, Long> {
     @Query(value = "select * from Task t where (select count((:tags))) = (select count(*) from task_tags tt where t.id = tt.task_id and tag_id in (:tags))", nativeQuery = true)
     List<Task> findDistinctByTagsContainingOrderByDeadline(List<Long> tags);
}
