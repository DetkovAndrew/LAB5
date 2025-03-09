package Commands.ElementsInnerOperating;

import CollectionManager.CollectionManager;
import Commands.CommandInterface.CommandInterface;
import SpaceMarine.SpaceMarine;

import java.util.Vector;

public class UpdateId implements CommandInterface {
    private final String name = "update_id";
    private final String description = "обновить элемент по id";
    private final CollectionManager collectionManager;

    public UpdateId(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
