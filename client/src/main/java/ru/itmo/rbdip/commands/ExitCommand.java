package ru.itmo.rbdip.commands;

public class ExitCommand extends Command {

    public ExitCommand(String url) {
        super(url);
    }

    @Override
    public void execute() {
        Command.authHeader = null;
    }

    @Override
    public String getName() {
        return "Exit";
    }
}
