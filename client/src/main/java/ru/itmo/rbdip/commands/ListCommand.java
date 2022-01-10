package ru.itmo.rbdip.commands;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpClientErrorException;
import ru.itmo.rbdip.data.Task;

public class ListCommand extends Command {

    public ListCommand(String url) {
        super(url);
    }

    @Override
    public void execute() {
        String nStr = null;
        int n = -1;
        do {
            System.out.print("Count of task(>0) (skip if show all): ");
            nStr = scanner.nextLine();
            if (nStr.isEmpty()) break;
            try {
                n = Integer.parseInt(nStr);
            } catch (Exception e) {
                System.out.println("Incorrect number");
            }
        } while (n <= 0);
        headers.set("Authorization", authHeader);
        try {
            String response = template.exchange(baseUrl + "task" + (n <= 0 ? "" : "?count=" + n), HttpMethod.GET, new HttpEntity<>(headers), String.class).getBody();
            System.out.println(response);
            Task[] tasks = gson.fromJson(response, Task[].class);
            for (Task task : tasks)
                System.out.println(task);
        } catch (HttpClientErrorException e) {
            System.out.println(e.getResponseBodyAsString());
        }
    }

    @Override
    public String getName() {
        return "Last tasks";
    }
}
