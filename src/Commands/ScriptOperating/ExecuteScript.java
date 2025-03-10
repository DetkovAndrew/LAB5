package Commands.ScriptOperating;

import Commands.CommandInterface.CommandInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Класс ExecuteScript реализует команду для выполнения скрипта из файла.
 * Команда является частью системы управления коллекцией и реализует интерфейс CommandInterface.
 * Использует переменные окружения SCRIPT_PATH и SPACE_MARINES_FILE для определения пути к файлу.
 *
 * @author Андрей
 * @version 1.0
 * @since 2025-03-10
 */
public class ExecuteScript implements CommandInterface {
    /** Название команды. */
    private final String name = "execute_script";

    /** Описание команды. */
    private final String description = "выполнить скрипт из файла";

    /** Карта доступных команд. */
    private final Map<String, CommandInterface> commands;

    /**
     * Конструктор класса ExecuteScript.
     *
     * @param commands Карта доступных команд.
     */
    public ExecuteScript(Map<String, CommandInterface> commands) {
        this.commands = commands;
    }

    /**
     * Выполняет команду ExecuteScript.
     * Считывает и исполняет скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
     *
     * @param args Аргументы команды. Проверяется наличие имени файла, строится полный путь до файла с помощью SCRIPT_PATH, скрипт исполняется построчно.
     */
    @Override
    public void execute(String[] args) {
        // Проверка наличия аргумента (имени файла)
        if (args.length < 1) {
            System.out.println("Использование: execute_script file_name");
            return;
        }
        String fileName = args[0].trim();

        String scriptPathEnv = System.getenv("SCRIPT_PATH");
        String spaceMarinesFileEnv = System.getenv("SPACE_MARINES_FILE");

        // Определение пути к файлу на основе переменных окружения
        String scriptPath;
        if (scriptPathEnv != null && !scriptPathEnv.trim().isEmpty()) {
            scriptPath = scriptPathEnv;
        } else if (spaceMarinesFileEnv != null && !spaceMarinesFileEnv.trim().isEmpty()) {
            Path spaceMarinesPath = Paths.get(spaceMarinesFileEnv);
            // Если SPACE_MARINES_FILE указывает на файл, берём его родительскую директорию
            if (spaceMarinesPath.toFile().isFile()) {
                scriptPath = spaceMarinesPath.getParent().toString();
            } else {
                scriptPath = spaceMarinesPath.toString();
            }
        } else {
            scriptPath = System.getProperty("user.dir");
        }

        // Формируем полный путь к файлу
        Path filePath = Paths.get(scriptPath, fileName);

        // Отладочный вывод для проверки пути
        System.out.println("Ищу файл по пути: " + filePath);

        // Проверка существования файла
        if (!filePath.toFile().exists()) {
            System.err.println("Ошибка: файл не найден по пути: " + filePath);
            return;
        }

        // Чтение и выполнение скрипта
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();
                // Пропуск пустых строк и комментариев
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                // Разделение команды и аргументов
                String[] parts = line.split("\\s+", 2);
                String commandName = parts[0].toLowerCase();
                String[] commandArgs;
                if (parts.length > 1) {
                    String argLine = parts[1].trim();
                    // Специальная обработка команды add с запятыми
                    if (commandName.equals("add")) {
                        commandArgs = parseCsvArgs(argLine);
                        if (commandArgs.length != 11) {
                            System.err.println("Ошибка в строке " + lineNumber + ": для команды add требуется ровно 11 аргументов, разделённых запятыми");
                            continue;
                        }
                    } else {
                        commandArgs = argLine.split("\\s+");
                    }
                } else {
                    commandArgs = new String[]{};
                }

                // Выполнение команды
                CommandInterface command = commands.get(commandName);
                if (command != null) {
                    command.execute(commandArgs);
                } else {
                    System.out.println("Предупреждение в строке " + lineNumber + ": команда не найдена: " + commandName);
                }
            }
            System.out.println("Скрипт выполнен успешно.");
        } catch (IOException e) {
            System.err.println("Ошибка при выполнении скрипта: " + e.getMessage());
        }
    }

    /**
     * Парсит строку с аргументами, разделёнными запятыми, с поддержкой кавычек.
     * @param argLine строка с аргументами
     * @return массив распарсенных аргументов
     */
    private String[] parseCsvArgs(String argLine) {
        List<String> args = new ArrayList<>();
        StringBuilder currentArg = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < argLine.length(); i++) {
            char c = argLine.charAt(i);
            if (c == '"') {
                inQuotes = !inQuotes;
                continue;
            }
            if (c == ',' && !inQuotes) {
                if (currentArg.length() > 0) {
                    args.add(currentArg.toString().trim());
                    currentArg = new StringBuilder();
                }
                continue;
            }
            currentArg.append(c);
        }
        if (currentArg.length() > 0) {
            args.add(currentArg.toString().trim());
        }

        return args.toArray(new String[0]);
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