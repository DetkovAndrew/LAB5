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

        // Переменные для хранения промежуточных данных
        String marineName = null;
        Integer x = null;
        Double y = null;
        Float health = null;
        String achievements = null;
        Weapon weaponType = null;
        MeleeWeapon meleeWeapon = null;
        String chapterName = null;
        String parentLegion = null;
        Integer marinesCount = null;
        String world = null;

        // Ввод имени
        while (true) {
            System.out.print("Введите имя: ");
            try {
                marineName = scanner.nextLine().trim();
                if (marineName.isEmpty()) {
                    System.out.println("Ошибка: имя не может быть пустым. Попробуйте снова.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage() + ". Попробуйте снова.");
            }
        }
        builder.setName(marineName);

        // Ввод координаты x
        while (true) {
            System.out.print("Введите координату x (целое число): ");
            try {
                x = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: x должен быть целым числом. Попробуйте снова.");
            }
        }

        // Ввод координаты y
        while (true) {
            System.out.print("Введите координату y (дробное число): ");
            try {
                y = Double.parseDouble(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: y должен быть дробным числом. Попробуйте снова.");
            }
        }
        builder.setCoordinates(new Coordinates(x, y));

        // Ввод здоровья
        while (true) {
            System.out.print("Введите здоровье (дробное число): ");
            try {
                health = Float.parseFloat(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: здоровье должно быть дробным числом. Попробуйте снова.");
            }
        }
        builder.setHealth(health);

        // Ввод достижений
        while (true) {
            System.out.print("Введите достижения: ");
            try {
                achievements = scanner.nextLine().trim();
                if (achievements.isEmpty()) {
                    System.out.println("Ошибка: достижения не могут быть пустыми. Попробуйте снова.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage() + ". Попробуйте снова.");
            }
        }
        builder.setAchievements(achievements);

        // Ввод типа оружия
        while (true) {
            System.out.print("Введите тип оружия (MELTAGUN, PLASMA_GUN, HEAVY_FLAMER): ");
            try {
                weaponType = Weapon.valueOf(scanner.nextLine().trim().toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: тип оружия должен быть одним из: MELTAGUN, PLASMA_GUN, HEAVY_FLAMER. Попробуйте снова.");
            }
        }
        builder.setWeaponType(weaponType);

        // Ввод ближнего оружия
        while (true) {
            System.out.print("Введите ближнее оружие (CHAIN_AXE, POWER_BLADE, POWER_FIST): ");
            try {
                meleeWeapon = MeleeWeapon.valueOf(scanner.nextLine().trim().toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: ближнее оружие должно быть одним из: CHAIN_AXE, POWER_BLADE, POWER_FIST. Попробуйте снова.");
            }
        }
        builder.setMeleeWeapon(meleeWeapon);

        // Ввод названия главы
        while (true) {
            System.out.print("Введите название главы (chapter name): ");
            try {
                chapterName = scanner.nextLine().trim();
                if (chapterName.isEmpty()) {
                    System.out.println("Ошибка: название главы не может быть пустым. Попробуйте снова.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage() + ". Попробуйте снова.");
            }
        }

        // Ввод родительского легиона
        while (true) {
            System.out.print("Введите родительский легион (parent legion): ");
            try {
                parentLegion = scanner.nextLine().trim();
                if (parentLegion.isEmpty()) {
                    System.out.println("Ошибка: родительский легион не может быть пустым. Попробуйте снова.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage() + ". Попробуйте снова.");
            }
        }

        // Ввод количества маринов
        while (true) {
            System.out.print("Введите количество маринов (marines count, целое число): ");
            try {
                marinesCount = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: количество маринов должно быть целым числом. Попробуйте снова.");
            }
        }

        // Ввод мира
        while (true) {
            System.out.print("Введите мир (world): ");
            try {
                world = scanner.nextLine().trim();
                if (world.isEmpty()) {
                    System.out.println("Ошибка: мир не может быть пустым. Попробуйте снова.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage() + ". Попробуйте снова.");
            }
        }
        builder.setChapter(new Chapter(chapterName, parentLegion, marinesCount, world));

        int newId = SpaceMarine.generateNextId(collection);
        builder.setId(newId);

        // Создаём SpaceMarine
        SpaceMarine marine = builder.build();

        // Добавляем в коллекцию
        collection.add(marine);
        System.out.println("Элемент добавлен с id " + newId + ": " + marine);
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