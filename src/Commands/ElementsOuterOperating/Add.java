package Commands.ElementsOuterOperating;

import Commands.CommandInterface.CommandInterface;
import SpaceMarine.SpaceMarine;

import java.util.Vector;

public class Add implements CommandInterface {
    private final String name = "add";
    private final String description = "добавить новый элемент в коллекцию";
    private final Vector<SpaceMarine> collection;

    public Add(Vector<SpaceMarine> collection) {
        this.collection = collection;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Использование: add id,name,x,y,health,achievements,weaponType,meleeWeapon,chapterName,parentLegion,marinesCount,world");
            return;
        }
        try {
            SpaceMarine marine = SpaceMarine.fromCSV(args[0]);
            collection.add(marine);
            System.out.println("Элемент добавлен: " + marine);
        } catch (Exception e) {
            System.out.println("Ошибка при добавлении: " + e.getMessage());
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
