package Commands.GeneralCommands;

import Commands.CommandInterface.CommandInterface;

import java.util.Map;

public class Help implements CommandInterface {
    private final String name = "help";
    private final String description = "вывести справку по доступным командам";
    private final Map<String, CommandInterface> commands;

    public Help(Map<String, CommandInterface> commands) {
        this.commands = commands;
    }

    @Override
    public void execute(String[] args) {
        System.out.println("Доступные команды:");
        for (Map.Entry<String, CommandInterface> entry : commands.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getDescription());
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
