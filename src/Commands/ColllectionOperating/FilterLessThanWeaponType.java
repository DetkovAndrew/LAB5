package Commands.ColllectionOperating;

import Commands.CommandInterface.CommandInterface;

import CollectionManager.CollectionManager;
import SpaceMarine.SpaceMarine;
import SpaceMarine.Weapon;
import java.util.Vector;

public class FilterLessThanWeaponType implements CommandInterface {
    private final String name = "filter_less_than_weapon_type";
    private final String description = "вывести элементы с weaponType меньше заданного";
    private final CollectionManager collectionManager;

    public FilterLessThanWeaponType(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Использование: filter_less_than_weapon_type weapon_type");
            return;
        }
        Vector<SpaceMarine> collection = collectionManager.getCollection();
        try {
            Weapon weaponType = Weapon.valueOf(args[0].toUpperCase());
            for (SpaceMarine marine : collection) {
                if (marine.getWeaponType().compareTo(weaponType) < 0) {
                    System.out.println(marine);
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Недопустимый тип оружия: " + args[0]);
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