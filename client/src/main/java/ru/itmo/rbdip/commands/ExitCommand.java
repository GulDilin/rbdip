package ru.itmo.rbdip.commands;

import java.util.Scanner;

public class ExitCommand extends Command{

    public ExitCommand(Scanner scanner, String url) {
        super(scanner, url);
    }

    @Override
    public void execute(){
        System.exit(0);
    }

    @Override
    public String getName() {
        return "Exit";
    }
}
