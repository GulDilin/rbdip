package ru.itmo.rbdip.commands;

import ru.itmo.rbdip.data.Task;

import java.util.*;

public class SearchCommand extends Command{

    public SearchCommand(Scanner scanner, String url) {
        super(scanner, url);
    }

    @Override
    public void execute() {
        System.out.print("Search tasks by tag: ");
        String tagsStr = scanner.nextLine();
        while (tagsStr.contains("  "))
            tagsStr = tagsStr.replaceAll("  "," ");

        String[] tags= tagsStr.split(" ");
        String response = template.getForEntity (baseUrl + "task?"+ getURLParams(tags), String.class).getBody();
        Task[] tasks = gson.fromJson(response, Task[].class);
        for(Task task: tasks)
            System.out.println(task);
    }


    String getURLParams(String[] tags){
        StringBuilder url = new StringBuilder();
        for(String tag: tags)
            url.append("tagTitles=").append(tag).append("&");
        return url.substring(0,url.length()-1);
    }

    @Override
    public String getName() {
        return "Search tasks";
    }
}
