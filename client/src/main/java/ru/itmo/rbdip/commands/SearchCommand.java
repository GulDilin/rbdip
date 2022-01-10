package ru.itmo.rbdip.commands;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpClientErrorException;
import ru.itmo.rbdip.data.Task;


public class SearchCommand extends Command {

    public SearchCommand(String url) {
        super(url);
    }

    @Override
    public void execute() {
        System.out.print("Search tasks by tag: ");
        String tagsStr = scanner.nextLine();
        while (tagsStr.contains("  "))
            tagsStr = tagsStr.replaceAll("  ", " ");

        String[] tags = tagsStr.split(" ");
        headers.set("Authorization", authHeader);
        try {
            String response = template.exchange(baseUrl + "task?" + getURLParams(tags), HttpMethod.GET, new HttpEntity<>(headers), String.class).getBody();
            Task[] tasks = gson.fromJson(response, Task[].class);
            for (Task task : tasks)
                System.out.println(task);
        } catch (HttpClientErrorException e) {
            System.out.println(e.getResponseBodyAsString());
        }
    }


    String getURLParams(String[] tags) {
        StringBuilder url = new StringBuilder();
        for (String tag : tags)
            url.append("tagTitles=").append(tag).append("&");
        return url.substring(0, url.length() - 1);
    }

    @Override
    public String getName() {
        return "Search tasks";
    }
}
