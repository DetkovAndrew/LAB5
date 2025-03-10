package Commands.ColllectionOperating;

import Commands.CommandInterface.CommandInterface;
import SpaceMarine.SpaceMarine;
import CollectionManager.CollectionManager;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

/**
 * Класс Save реализует команду для сохранения коллекции объектов SpaceMarine в файл.
 * Команда является частью системы управления коллекцией и реализует интерфейс CommandInterface.
 * Сохранение выполняется в файл, указанный в переменной окружения SPACE_MARINES_FILE, или в файл по умолчанию.
 *
 * @author Андрей
 * @version 1.0
 * @since 2025-03-10
 */
public class Save implements CommandInterface {
    /** Название команды. */
    private final String name = "save";

    /** Описание команды. */
    private final String description = "сохранить коллекцию в файл";

    /** Менеджер коллекции, с которой работает команда. */
    private final CollectionManager collectionManager;

    /** Путь к файлу, в который сохраняется коллекция (не используется в текущей реализации). */
    private String filePath;

    /**
     * Конструктор класса Save.
     *
     * @param collectionManager Менеджер коллекции, который предоставляет доступ к коллекции SpaceMarine.
     * @param filePath Путь к файлу для сохранения (в текущей реализации игнорируется).
     */
    public Save(CollectionManager collectionManager, String filePath) {
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду save.
     * Сохраняет коллекцию объектов SpaceMarine в файл в формате CSV.
     * Путь к файлу берётся из переменной окружения SPACE_MARINES_FILE, либо используется файл по умолчанию.
     *
     * @param args Аргументы команды (не используются в данной команде).
     * @throws IOException если произошла ошибка при записи в файл
     */
    @Override
    public void execute(String[] args) {
        Vector<SpaceMarine> collection = collectionManager.getCollection();
        String filePath = System.getenv("SPACE_MARINES_FILE") != null ? System.getenv("SPACE_MARINES_FILE") : "SPACE_MARINES_FILE.csv";
        try (FileWriter writer = new FileWriter(filePath)) {
            for (SpaceMarine marine : collection) {
                writer.write(marine.toCSV() + "\n");
            }
            System.out.println("Коллекция сохранена в файл: " + filePath);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении: " + e.getMessage());
        }
    }

    /**
     * Возвращает название команды.
     *
     * @return Название команды ("save").
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Возвращает описание команды.
     *
     * @return Описание команды.
     */
    @Override
    public String getDescription() {
        return description;
    }
}
