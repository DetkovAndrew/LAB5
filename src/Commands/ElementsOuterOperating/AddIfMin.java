package Commands.ElementsOuterOperating;

import Commands.CommandInterface.CommandInterface;
import SpaceMarine.SpaceMarine;

import java.util.Collections;
import java.util.Vector;

public class AddIfMin implements CommandInterface {
    private final String name = "add_if_min";
    private final String description = "добавить элемент, если он меньше минимального";
    private final Vector<SpaceMarine> collection;

    public AddIfMin(Vector<SpaceMarine> collection) {
        this.collection = collection;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Использование: add_if_min id,name,x,y,health,achievements,weaponType,meleeWeapon,chapterName,parentLegion,marinesCount,world");
            return;
        }
        try {
            SpaceMarine newMarine = SpaceMarine.fromCSV(args[0]);
            if (collection.isEmpty() || newMarine.compareTo(Collections.min(collection)) < 0) {
                collection.add(newMarine);
                System.out.println("Элемент добавлен как минимальный: " + newMarine);
            } else {
                System.out.println("Элемент не добавлен, так как не является минимальным.");
            }
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
