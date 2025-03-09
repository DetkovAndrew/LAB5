package Commands.ElementsOuterOperating;

import CollectionManager.CollectionManager;
import Commands.CommandInterface.CommandInterface;
import SpaceMarine.SpaceMarine;
import SpaceMarine.*;

import java.util.Scanner;
import java.util.Vector;

public class Add implements CommandInterface {
    private final String name = "add";
    private final String description = "добавить новый элемент в коллекцию";
    private final CollectionManager collectionManager;

    public Add(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        Vector<SpaceMarine> collection = collectionManager.getCollection();
        Scanner scanner = new Scanner(System.in);
        SpaceMarine.Builder builder = new SpaceMarine.Builder();

        try {
            // Запрашиваем данные у пользователя пошагово
            System.out.print("Введите имя: ");
            String marineName = scanner.nextLine().trim();
            builder.setName(marineName);

            System.out.print("Введите координату x (целое число): ");
            int x = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Введите координату y (дробное число): ");
            double y = Double.parseDouble(scanner.nextLine().trim());
            builder.setCoordinates(new Coordinates(x, y));

            System.out.print("Введите здоровье (дробное число): ");
            float health = Float.parseFloat(scanner.nextLine().trim());
            builder.setHealth(health);

            System.out.print("Введите достижения: ");
            String achievements = scanner.nextLine().trim();
            builder.setAchievements(achievements);

            System.out.print("Введите тип оружия (MELTAGUN, PLASMA_GUN, HEAVY_FLAMER): ");
            Weapon weaponType = Weapon.valueOf(scanner.nextLine().trim().toUpperCase());
            builder.setWeaponType(weaponType);

            System.out.print("Введите ближнее оружие (CHAIN_AXE, POWER_BLADE, POWER_FIST): ");
            MeleeWeapon meleeWeapon = MeleeWeapon.valueOf(scanner.nextLine().trim().toUpperCase());
            builder.setMeleeWeapon(meleeWeapon);

            System.out.print("Введите название главы (chapter name): ");
            String chapterName = scanner.nextLine().trim();
            System.out.print("Введите родительский легион (parent legion): ");
            String parentLegion = scanner.nextLine().trim();
            System.out.print("Введите количество маринов (marines count, целое число): ");
            int marinesCount = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Введите мир (world): ");
            String world = scanner.nextLine().trim();
            builder.setChapter(new Chapter(chapterName, parentLegion, marinesCount, world));

            // Генерируем id
            int newId = SpaceMarine.generateNextId(collection);
            builder.setId(newId);

            // Создаём SpaceMarine
            SpaceMarine marine = builder.build();

            // Добавляем в коллекцию
            collection.add(marine);
            System.out.println("Элемент добавлен с id " + newId + ": " + marine);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: неверный формат числа. Попробуйте снова.");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Произошла неизвестная ошибка: " + e.getMessage());
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