package ru.itmo.rbdip;

import ru.itmo.rbdip.commands.*;

import java.util.EnumMap;
import java.util.Scanner;

import static ru.itmo.rbdip.CommandEnum.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EnumMap<CommandEnum, Command> unAuthorizeCommands = new EnumMap<CommandEnum, Command>(CommandEnum.class) {{
            put(LOGIN_COMMAND, new AuthCommand(args[0], false));
            put(REGISTER_COMMAND, new AuthCommand(args[0], true));
        }};
        EnumMap<CommandEnum, Command> authorizeCommands = new EnumMap<CommandEnum, Command>(CommandEnum.class) {{
            put(ADD_COMMAND, new AddCommand(args[0]));
            put(SEARCH_COMMAND, new SearchCommand(args[0]));
            put(LIST_COMMAND, new ListCommand(args[0]));
            put(EXIT_COMMAND, new ExitCommand(args[0]));
        }};
        EnumMap<CommandEnum, Command> commands = unAuthorizeCommands;

        printMenu(commands);
        int commandOrder = 0;
        while (commandOrder != QUIT_COMMAND.ordinal() + 1) {
            try {
                System.out.print("> ");
                String commandStr = scanner.nextLine();
                if (commandStr.equals(""))
                    continue;
                commandOrder = Integer.parseInt(commandStr);
                CommandEnum command = CommandEnum.values()[commandOrder - 1];
                commands.get(command).execute();
                commands = Command.authHeader == null ? unAuthorizeCommands : authorizeCommands;
                printMenu(commands);
            } catch (NumberFormatException e) {
                System.out.println("Incorrect number");
            } catch (Exception e) {
                System.out.println("No such command");
            }
        }
    }

    static void printMenu(EnumMap<CommandEnum, Command> commands) {
        System.out.println("Menu:");
        for (CommandEnum ce : commands.keySet())
            System.out.println(ce.ordinal() + 1 + " " + commands.get(ce).getName());
        System.out.println(QUIT_COMMAND.ordinal() + 1 + " Quit app");
    }
}
