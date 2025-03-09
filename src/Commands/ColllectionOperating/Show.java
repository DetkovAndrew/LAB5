package Commands.ColllectionOperating;

import Commands.CommandInterface.CommandInterface;
import SpaceMarine.SpaceMarine;
import CollectionManager.CollectionManager;
import java.util.Vector;

public class Show implements CommandInterface {
    private final String name = "show";
    private final String description = "вывести все элементы коллекции";
    private final CollectionManager collectionManager;

    public Show(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        Vector<SpaceMarine> collection = collectionManager.getCollection();
        if (collection.isEmpty()) {
            System.out.println("Коллекция пуста.");
        } else {
            for (SpaceMarine marine : collection) {
                System.out.println(marine);
            }
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
