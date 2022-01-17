package ru.itmo.rbdip.commands;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import ru.itmo.rbdip.data.Task;
import ru.itmo.rbdip.data.TaskData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddCommand extends Command {

    public AddCommand(String url) {
        super(url);
    }

    @Override
    public void execute() {
        System.out.println("New task");
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        Date deadline = null;
        while (deadline == null)
            try {
                System.out.print("Deadline(dd.MM.yyyy): ");
                deadline = new SimpleDateFormat("dd.MM.yyyy").parse(scanner.nextLine());
            } catch (ParseException e) {
                System.out.println("Incorrect date");
            }

        System.out.println("Tags (finish on empty line)");
        List<String> tags = new ArrayList<>();
        String tag;
        System.out.print(1 + " ");
        while (!(tag = scanner.nextLine()).equals("")) {
            tags.add(tag);
            System.out.print(tags.size() + 1 + " ");
        }
        String request = gson.toJson(new TaskData(title, description, deadline, tags));
        headers.set("Authorization", authHeader);
        HttpEntity<String> entity = new HttpEntity<>(request, headers);
        ResponseEntity<String> response = null;
        try {
            response = template.postForEntity(baseUrl + "task", entity, String.class);
            Task task = gson.fromJson(response.getBody(), Task.class);
            if (response.getStatusCode() == HttpStatus.CREATED)
                System.out.println(task);
            else System.out.println(response);
        } catch (HttpClientErrorException e) {
            System.out.println(e.getResponseBodyAsString());
        }
    }

    @Override
    public String getName() {
        return "Add task";
    }
}
