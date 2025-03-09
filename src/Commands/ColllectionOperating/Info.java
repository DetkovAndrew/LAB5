package Commands.ColllectionOperating;

import Commands.CommandInterface.CommandInterface;
import CollectionManager.CollectionManager;

import java.util.Vector;

public class Info implements CommandInterface {
    private final String name = "info";
    private final String description = "вывести информацию о коллекции";
    private final CollectionManager collectionManager;

    public Info(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        Vector<?> collection = collectionManager.getCollection();
        System.out.println("Тип коллекции: " + collection.getClass().getSimpleName());
        System.out.println("Количество элементов: " + collection.size());
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
