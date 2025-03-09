package Commands.ScriptOperating;

import Commands.CommandInterface.CommandInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class ExecuteScript implements CommandInterface {
    private final String name = "execute_script";
    private final String description = "выполнить скрипт из файла";
    private final Map<String, CommandInterface> commands;

    public ExecuteScript(Map<String, CommandInterface> commands) {
        this.commands = commands;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Использование: execute_script file_name");
            return;
        }
        String fileName = args[0];
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+", 2);
                String commandName = parts[0];
                String[] commandArgs = parts.length > 1 ? parts[1].split("\\s+") : new String[]{};
                CommandInterface command = commands.get(commandName);
                if (command != null) {
                    command.execute(commandArgs);
                } else {
                    System.out.println("Команда не найдена: " + commandName);
                }
            }
            System.out.println("Скрипт выполнен.");
        } catch (IOException e) {
            System.err.println("Ошибка при выполнении скрипта: " + e.getMessage());
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
