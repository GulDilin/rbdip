package ru.itmo.rbdip.commands;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import ru.itmo.rbdip.data.TaskData;
import ru.itmo.rbdip.data.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AddCommand extends Command{

    public AddCommand(Scanner scanner, String url) {
        super(scanner, url);
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
                deadline =  new SimpleDateFormat("dd.MM.yyyy").parse(scanner.nextLine());
            } catch (ParseException e) {
                System.out.println("Incorrect date");
            }

        System.out.println("Tags (finish on empty line)");
        List<String> tags  = new ArrayList<>();
        String tag;
        System.out.print(1+" ");
        while (!(tag = scanner.nextLine()).equals("")) {
            tags.add(tag);
            System.out.print(tags.size()+1+" ");
        }
        String request = gson.toJson(new TaskData(title,description,deadline,tags));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(request, headers);
        ResponseEntity<String> response = null;
        try {
            response = template.postForEntity(baseUrl+"task",entity,String.class);
            Task task = gson.fromJson(response.getBody(), Task.class);
            System.out.println(task);
        }catch (HttpClientErrorException e){
            System.out.println(response.getBody());
        }
    }

    @Override
    public String getName() {
       return  "Add task";
    }
}
