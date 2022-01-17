package ru.itmo.rbdip.service;

import org.springframework.stereotype.Service;
import ru.itmo.rbdip.repository.TagRepository;
import ru.itmo.rbdip.repository.TaskRepository;
import ru.itmo.rbdip.repository.data.TaskData;
import ru.itmo.rbdip.repository.entity.Tag;
import ru.itmo.rbdip.repository.entity.Task;
import ru.itmo.rbdip.repository.entity.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    TaskRepository repository;
    TagRepository tagRepository;

    public TaskService(TaskRepository repository, TagRepository tagInterface) {
        this.repository = repository;
        this.tagRepository = tagInterface;
    }

    public List<Task> searchTasks(User user, List<String> tagTitles, Integer count) {
        if (tagTitles == null || tagTitles.isEmpty())
            if (count == null)
                return repository.findTasksByUserOrderByDeadline(user);
            else
                return repository.findTasksByUserWithLimit(user.getId(), count);

        List<Tag> tags = tagRepository.findAllByTitleIn(tagTitles);
        List<Long> tagIds = new ArrayList<>();
        for (Tag tag : tags) tagIds.add(tag.getId());
        return repository.findDistinctByTagsContainingOrderByDeadline(user.getId(), tagIds);

    }


    public Object createTask(TaskData taskdata, User user) {
        List<String> tagDatas = taskdata.getTags();
        List<Tag> tags = tagRepository.findAllByTitleIn(tagDatas);
        for (String tagData : tagDatas) {
            Tag tag = tagRepository.getByTitle(tagData);
            if (tag == null) {
                tag = tagRepository.save(new Tag(0L, tagData));
                tags.add(tag);
            }
        }

        Task task = new Task(0L, taskdata.getTitle(), taskdata.getDescription(), taskdata.getDeadline(), tags, user);
        try {
            task = repository.save(task);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }

        return task;
    }

}
