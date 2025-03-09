package Commands.ColllectionOperating;

import Commands.CommandInterface.CommandInterface;

import CollectionManager.CollectionManager;
import SpaceMarine.SpaceMarine;
import java.util.Vector;

public class AverageOfHealth implements CommandInterface {
    private final String name = "average_of_health";
    private final String description = "вывести среднее значение поля health для всех элементов коллекции";
    private final CollectionManager collectionManager;

    public AverageOfHealth(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        Vector<SpaceMarine> collection = collectionManager.getCollection();
        if (collection.isEmpty()) {
            System.out.println("Коллекция пуста.");
            return;
        }
        float sum = 0;
        int count = 0;
        for (SpaceMarine marine : collection) {
            if (marine.getHealth() != 0) {
                sum += marine.getHealth();
                count++;
            }
        }
        System.out.println("Среднее значение health: " + (count > 0 ? sum / count : 0));
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
