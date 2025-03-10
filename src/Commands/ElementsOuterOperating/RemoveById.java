package Commands.ElementsOuterOperating;

import CollectionManager.CollectionManager;
import Commands.CommandInterface.CommandInterface;
import SpaceMarine.SpaceMarine;

import java.util.Vector;

/**
 * Класс RemoveById реализует команду для удаления элемента из коллекции по его идентификатору (id).
 * Команда является частью системы управления коллекцией и реализует интерфейс CommandInterface.
 *
 * @author Андрей
 * @version 1.0
 * @since 2025-03-10
 */
public class RemoveById implements CommandInterface {
    /** Название команды. */
    private final String name = "remove_by_id";

    /** Описание команды. */
    private final String description = "удалить элемент по id";

    /** Менеджер коллекции, с которой работает команда. */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса RemoveById.
     *
     * @param collectionManager Менеджер коллекции, который предоставляет доступ к коллекции SpaceMarine.
     */
    public RemoveById(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду remove_by_id.
     * Удаляет элемент из коллекции SpaceMarine по указанному идентификатору.
     * Если аргументов недостаточно, id некорректен или элемент не найден, выводит сообщение.
     *
     * @param args Аргументы команды, где args[0] — идентификатор элемента для удаления (целое число).
     *             Если args пустой, выводится сообщение об использовании команды.
     * @throws NumberFormatException если id не является числом
     */
    @Override
    public void execute(String[] args) {
        Vector<SpaceMarine> collection = collectionManager.getCollection();
        if (args.length < 1) {
            System.out.println("Использование: remove_by_id id");
            return;
        }
        try {
            int id = Integer.parseInt(args[0]);
            if (collection.removeIf(marine -> marine.getId() == id)) {
                System.out.println("Элемент с id " + id + " удалён.");
            } else {
                System.out.println("Элемент с id " + id + " не найден.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID должен быть числом.");
        }
    }

    /**
     * Возвращает название команды.
     *
     * @return Название команды ("remove_by_id").
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
