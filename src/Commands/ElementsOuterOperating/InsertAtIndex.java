package Commands.ElementsOuterOperating;

import Commands.CommandInterface.CommandInterface;
import SpaceMarine.SpaceMarine;

import java.util.Vector;

public class InsertAtIndex implements CommandInterface {
    private final String name = "insert_at_index";
    private final String description = "вставить элемент на указанную позицию";
    private final Vector<SpaceMarine> collection;

    public InsertAtIndex(Vector<SpaceMarine> collection) {
        this.collection = collection;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Использование: insert_at_index index id,name,x,y,health,achievements,weaponType,meleeWeapon,chapterName,parentLegion,marinesCount,world");
            return;
        }
        try {
            int index = Integer.parseInt(args[0]);
            SpaceMarine marine = SpaceMarine.fromCSV(args[1]);
            if (index >= 0 && index <= collection.size()) {
                collection.add(index, marine);
                System.out.println("Элемент вставлен на позицию " + index);
            } else {
                System.out.println("Недопустимый индекс: " + index);
            }
        } catch (NumberFormatException e) {
            System.out.println("Индекс должен быть числом.");
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
