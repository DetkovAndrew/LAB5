package Commands.ElementsOuterOperating;

import Commands.CommandInterface.CommandInterface;
import CollectionManager.CollectionManager;

import SpaceMarine.SpaceMarine;

import java.util.Vector;

public class RemoveLast implements CommandInterface {
    private final String name = "remove_last";
    private final String description = "удалить последний элемент";
    private final CollectionManager collectionManager;

    public RemoveLast(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        Vector<SpaceMarine> collection = collectionManager.getCollection();
        if (!collection.isEmpty()) {
            collection.remove(collection.size() - 1);
            System.out.println("Последний элемент удалён.");
        } else {
            System.out.println("Коллекция пуста.");
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
