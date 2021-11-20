package ru.itmo.rbdip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.rbdip.repository.TagInterface;
import ru.itmo.rbdip.repository.TaskRepository;
import ru.itmo.rbdip.repository.entity.Tag;
import ru.itmo.rbdip.repository.entity.Task;
import ru.itmo.rbdip.repository.data.TaskData;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/task")
@RestController
class TaskController {

    @Autowired
    TaskRepository repository;

    @Autowired
    TagInterface tagInterface;

    @GetMapping
    //@PreAuthorize("hasAnyRole('BORROWER_CONFIRMED')")
    ResponseEntity<List<Task>> searchTasks(@RequestParam List<Tag> tags) {
        return ResponseEntity.ok(repository.findAllByTagsOrderByDeadlineAsc(tags));
    }

    @PostMapping
    ResponseEntity<Task> createTask(@RequestBody TaskData taskdata){
      List<String> tagDatas = taskdata.getTags();
      List<Tag> tags = new ArrayList<>();
      for(String tagData: tagDatas){
          Tag tag = new Tag(0L,tagData);
          tag = tagInterface.save(tag);
          tags.add(tag);
      }

      Task task = new Task(0L,taskdata.getTitle(),taskdata.getDescription(),taskdata.getDeadline(),tags);
      task = repository.save(task);

      return ResponseEntity.ok(task);
    }


}