package Commands.ColllectionOperating;

import Commands.CommandInterface.CommandInterface;
import CollectionManager.CollectionManager;
import SpaceMarine.SpaceMarine;

import java.util.Vector;

public class Clear implements CommandInterface {
    private final String name = "clear";
    private final String description = "очистить коллекцию";
    private final CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        Vector<SpaceMarine> collection = collectionManager.getCollection();
        collection.clear();
        System.out.println("Коллекция очищена.");
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