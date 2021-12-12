package ru.itmo.rbdip;

import ru.itmo.rbdip.commands.*;

import java.util.EnumMap;
import java.util.Scanner;

import static ru.itmo.rbdip.CommandEnum.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EnumMap<CommandEnum, Command> commands = new EnumMap<CommandEnum, Command>(CommandEnum.class) {{
            put(ADD_COMMAND, new AddCommand(scanner, args[0]));
            put(SEARCH_COMMAND, new SearchCommand(scanner, args[0]));
            put(LIST_COMMAND, new ListCommand(scanner,args[0]));
            put(EXIT_COMMAND, new ExitCommand(scanner,args[0]));
        }};

        printMenu(commands);
        int commandOrder = 0;
        while (commandOrder != EXIT_COMMAND.ordinal() + 1) {
            try {
                System.out.print("> ");
                String commandStr = scanner.nextLine();
                if (commandStr.equals(""))
                    continue;
                commandOrder = Integer.parseInt(commandStr);
                if (commandOrder > 0 && commandOrder <= commands.size()) {
                    CommandEnum command = CommandEnum.values()[commandOrder - 1];
                    commands.get(command).execute();
                } else
                    System.out.println("No such command");
            } catch (NumberFormatException e) {
                System.out.println("Incorrect number");
            }
        }
    }

    static void printMenu(EnumMap<CommandEnum, Command> commands) {
        System.out.println("Menu:");
        for (CommandEnum ce : commands.keySet())
            System.out.println(ce.ordinal() + 1 + " " + commands.get(ce).getName());
    }
}
