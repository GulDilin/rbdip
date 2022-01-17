package ru.itmo.rbdip.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.rbdip.repository.data.TaskData;
import ru.itmo.rbdip.repository.entity.Task;
import ru.itmo.rbdip.repository.entity.User;
import ru.itmo.rbdip.service.TaskService;
import ru.itmo.rbdip.service.UserService;

import java.util.List;


@CrossOrigin(origins = "**", maxAge = 3600)
@RequestMapping("/api/task")
@RestController
class TaskController {

    TaskService taskService;
    UserService userService;

    public TaskController(TaskService service, UserService userService) {
        this.taskService = service;
        this.userService = userService;
    }

    @GetMapping
    ResponseEntity<List<Task>> searchTasks(
            @RequestParam(required = false) List<String> tagTitles,
            @RequestParam(required = false) Integer count,
            @RequestHeader("Authorization") String authHeader) {

        User user = userService.authorizeUserByHeader(authHeader);
        List<Task> tasks = taskService.searchTasks(user, tagTitles, count);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Object> createTask(
            @RequestBody TaskData taskdata,
            @RequestHeader("Authorization") String authHeader) {
        User user = userService.authorizeUserByHeader(authHeader);
        return new ResponseEntity<>(taskService.createTask(taskdata, user), HttpStatus.CREATED);
    }


}