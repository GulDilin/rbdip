package ru.itmo.rbdip.commands;


import ru.itmo.rbdip.data.Task;

import java.util.Scanner;

public class ListCommand extends Command{

    public ListCommand(Scanner scanner, String url) {
        super(scanner, url);
    }

    @Override
    public void execute() {
        String nStr = null;
        int n = -1;
         do {
             System.out.print("Count of task(>0) (skip if show all): ");
             nStr = scanner.nextLine();
             if ("".equals(nStr))
                 break;
             try {
                 n = Integer.parseInt(nStr);
             }catch (NumberFormatException e){
                 System.out.println("Incorrect number");
             }
         }while (n <= 0);
        String response = template.getForEntity(baseUrl + "task"+(n<=0?"":"?count="+n), String.class).getBody();
        Task[] tasks = gson.fromJson(response, Task[].class);
        for(Task task: tasks)
            System.out.println(task);
    }

    @Override
    public String getName() {
        return "Last tasks";
    }
}
