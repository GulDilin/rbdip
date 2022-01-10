package ru.itmo.rbdip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itmo.rbdip.repository.entity.Task;
import ru.itmo.rbdip.repository.entity.User;

import java.util.List;

@Repository

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(value = "select * from Task t where exists(select * from task_tags tt where t.id = tt.task_id and tag_id in (:tags)) and :userId=t.owner_id  order by t.deadline ;", nativeQuery = true)
    List<Task> findDistinctByTagsContainingOrderByDeadline(Long userId, List<Long> tags);

    List<Task> findTasksByUserOrderByDeadline(User user);

    @Query(value = "select * from Task t where t.owner_id=:userId order by deadline limit :limit ;", nativeQuery = true)
    List<Task> findTasksByUserWithLimit(Long userId, Integer limit);
}
