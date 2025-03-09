package Commands.GeneralCommands;

import Commands.CommandInterface.CommandInterface;

public class Exit implements CommandInterface {
    private final String name = "exit";
    private final String description = "завершить программу";

    @Override
    public void execute(String[] args) {
        System.out.println("Программа завершена.");
        System.exit(0);
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
