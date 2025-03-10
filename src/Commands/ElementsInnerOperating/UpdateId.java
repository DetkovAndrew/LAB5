package Commands.ElementsInnerOperating;

import CollectionManager.CollectionManager;
import Commands.CommandInterface.CommandInterface;
import SpaceMarine.SpaceMarine;

import java.util.Vector;

/**
 * Класс UpdateId реализует команду для обновления элемента коллекции по его идентификатору (id).
 * Команда позволяет заменить существующий элемент коллекции SpaceMarine на новый с сохранением старого id.
 * Является частью системы управления коллекцией и реализует интерфейс CommandInterface.
 *
 * @author Андрей
 * @version 1.0
 * @since 2025-03-10
 */
public class UpdateId implements CommandInterface {
    /** Название команды. */
    private final String name = "update_id";

    /** Описание команды. */
    private final String description = "обновить элемент по id";

    /** Менеджер коллекции, с которой работает команда. */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса UpdateId.
     *
     * @param collectionManager Менеджер коллекции, который предоставляет доступ к коллекции SpaceMarine.
     */
    public UpdateId(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду update_id.
     * Обновляет элемент коллекции с указанным id, заменяя его на новый объект, созданный из CSV-строки.
     * Если аргументов недостаточно, id некорректен или элемент не найден, выводит соответствующее сообщение.
     *
     * @param args Аргументы команды, где:
     *             args[0] — идентификатор элемента для обновления (целое число),
     *             args[1] — строка в формате CSV для создания нового элемента (id,name,x,y,health,achievements,weaponType,meleeWeapon,chapterName,parentLegion,marinesCount,world).
     *             Если args.length < 2, выводится сообщение об использовании команды.
     * @throws NumberFormatException если аргумент id не является числом
     * @throws Exception если произошла ошибка при парсинге CSV-строки
     */
    @Override
    public void execute(String[] args) {
        Vector<SpaceMarine> collection = collectionManager.getCollection();
        if (args.length < 2) {
            System.out.println("Использование: update_id id id,name,x,y,health,achievements,weaponType,meleeWeapon,chapterName,parentLegion,marinesCount,world");
            return;
        }
        try {
            int oldId = Integer.parseInt(args[0]);
            SpaceMarine newMarine = SpaceMarine.fromCSV(args[1]);
            newMarine.setId(oldId); // Устанавливаем старый id
            for (int i = 0; i < collection.size(); i++) {
                if (collection.get(i).getId() == oldId) {
                    collection.set(i, newMarine);
                    System.out.println("Элемент с id " + oldId + " обновлён.");
                    return;
                }
            }
            System.out.println("Элемент с id " + oldId + " не найден.");
        } catch (NumberFormatException e) {
            System.out.println("ID должен быть числом.");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    /**
     * Возвращает название команды.
     *
     * @return Название команды ("update_id").
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
