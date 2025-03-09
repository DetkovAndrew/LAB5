package Commands.ElementsOuterOperating;

import CollectionManager.CollectionManager;
import Commands.CommandInterface.CommandInterface;
import SpaceMarine.SpaceMarine;

import java.util.Vector;

public class RemoveById implements CommandInterface {
    private final String name = "remove_by_id";
    private final String description = "удалить элемент по id";
    private final CollectionManager collectionManager;

    public RemoveById(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
