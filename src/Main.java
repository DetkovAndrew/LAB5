import Commands.CommandInterface.CommandInterface;
import Commands.Register.CommandsRegister;
import CollectionManager.CollectionManager;
import SpaceMarine.SpaceMarine;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        // Инициализация CollectionManager для загрузки коллекции
        CollectionManager collectionManager = new CollectionManager();
        Vector<SpaceMarine> collection = collectionManager.getCollection();

        // Регистрация всех команд
        CommandsRegister.registerCommands();

        // Получение мапы команд из реестра
        Map<String, CommandInterface> commands = CommandsRegister.getCommands();

        // Запуск консольного ввода
        Scanner scanner = new Scanner(System.in);
        System.out.println("Программа запущена. Введите команду (help для справки):");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                continue;
            }

            // Разбиваем ввод на команду и аргументы
            String[] parts = input.split("\\s+", 2); // Делим на команду и остаток
            String commandName = parts[0].toLowerCase(); // Приводим к нижнему регистру для стабильности
            String[] commandArgs = parts.length > 1 ? parts[1].split("\\s+") : new String[]{};

            // Обработка аргументов для команд, требующих сложного парсинга (например, add и update_id)
            if (commandName.equals("add") && parts.length > 1) {
                commandArgs = new String[]{parts[1]}; // Всё после "add" — один аргумент (CSV-строка)
            } else if (commandName.equals("update_id") && parts.length > 1) {
                String[] updateParts = parts[1].split("\\s+", 2); // Делим на id и CSV-строку
                commandArgs = updateParts.length == 2 ? new String[]{updateParts[0], updateParts[1]} : new String[]{};
            } else if (commandName.equals("insert_at_index") && parts.length > 1) {
                String[] insertParts = parts[1].split("\\s+", 2); // Делим на index и CSV-строку
                commandArgs = insertParts.length == 2 ? new String[]{insertParts[0], insertParts[1]} : new String[]{};
            }

            // Выполнение команды
            CommandInterface command = commands.get(commandName);
            if (command != null) {
                command.execute(commandArgs);
            } else {
                System.err.println("Команда не найдена: " + commandName);
            }
        }
    }
}