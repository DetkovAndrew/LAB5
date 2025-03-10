package Commands.Register;

import Commands.ColllectionOperating.*;
import Commands.CommandInterface.CommandInterface;
import Commands.ElementsInnerOperating.UpdateId;
import Commands.ElementsOuterOperating.*;
import Commands.GeneralCommands.Exit;
import Commands.GeneralCommands.Help;
import Commands.ScriptOperating.ExecuteScript;
import CollectionManager.CollectionManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс CommandsRegister реализует управление и запись команд.
 *
 * @author Андрей
 * @version 1.0
 * @since 2025-03-10
 */
public class CommandsRegister {
    /** Карта доступных команд. */
    private static final Map<String, CommandInterface> commandMap = new HashMap<>();

    /** Карта доступных команд. */
    private static CollectionManager collectionManager = new CollectionManager();

    /** filePath для получения доступа к коллекции (через переменную окружения). */
    private static final String filePath = System.getenv("SPACE_MARINES_FILE") != null ? System.getenv("SPACE_MARINES_FILE") : "data.csv";

    /**
     * Метод, заполняющий карту команд существующими командами.
     * Производит выход из консольного приложения.
     * Использует существующий filePath для доступа к коллекции.
     * Сначала добавляет команды, которые не взаимодействуют напрямую с коллекцией, затем те, которые зависят от коллекции.
     * Доступ к коллекции осуществляется через класс collectionManager
     */
    public static void registerCommands() {
        System.out.println("Используемый filePath: " + filePath);
        // Сначала добавляем команды, которые зависят от commandMap
        commandMap.put("help", new Help(commandMap));
        commandMap.put("execute_script", new ExecuteScript(commandMap));

        // Добавляем остальные команды с коллекцией и другими аргументами
        commandMap.put("info", new Info(collectionManager));
        commandMap.put("show", new Show(collectionManager));
        commandMap.put("clear", new Clear(collectionManager));
        commandMap.put("save", new Save(collectionManager, filePath));
        commandMap.put("average_of_health", new AverageOfHealth(collectionManager));
        commandMap.put("count_greater_than_chapter", new CountGreaterThanChapter(collectionManager));
        commandMap.put("filter_less_than_weapon_type", new FilterLessThanWeaponType(collectionManager));
        commandMap.put("add", new Add(collectionManager));
        commandMap.put("add_if_min", new AddIfMin(collectionManager));
        commandMap.put("insert_at_index", new InsertAtIndex(collectionManager));
        commandMap.put("remove_by_id", new RemoveById(collectionManager));
        commandMap.put("remove_last", new RemoveLast(collectionManager));
        commandMap.put("update_id", new UpdateId(collectionManager));
        commandMap.put("exit", new Exit());
    }

    /**
     * Статический метод, возвращающий карту зарегистрированных команд.
     */
    public static Map<String, CommandInterface> getCommands() {
        return new HashMap<>(commandMap);
    }
}
