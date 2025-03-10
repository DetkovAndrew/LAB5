package Commands.ElementsOuterOperating;

import CollectionManager.CollectionManager;
import Commands.CommandInterface.CommandInterface;
import SpaceMarine.SpaceMarine;
import SpaceMarine.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

/**
 * Класс Add реализует команду для добавления нового элемента в коллекцию объектов SpaceMarine.
 * Команда поддерживает два режима ввода: интерактивный (через консоль) и через аргументы в формате CSV.
 * Является частью системы управления коллекцией и реализует интерфейс CommandInterface.
 *
 * @author Андрей
 * @version 1.0
 * @since 2025-03-10
 */
public class Add implements CommandInterface {
    /** Название команды. */
    private final String name = "add";

    /** Описание команды. */
    private final String description = "добавить новый элемент в коллекцию";

    /** Менеджер коллекции, с которой работает команда. */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса Add.
     *
     * @param collectionManager Менеджер коллекции, который предоставляет доступ к коллекции SpaceMarine.
     */
    public Add(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду add.
     * Добавляет новый объект SpaceMarine в коллекцию. Поддерживает два режима:
     * - Интерактивный: запрашивает данные у пользователя через консоль.
     * - Через аргументы: принимает строку в формате CSV с 11 полями.
     * Генерирует уникальный id для нового элемента и выводит информацию о добавлении.
     *
     * @param args Аргументы команды, где args[0] — строка в формате CSV (name, x, y, health, achievements, weaponType, meleeWeapon, chapterName, parentLegion, marinesCount, world),
     *             если используется аргументный режим. Если args пустой, запускается интерактивный режим.
     * @throws NumberFormatException если введённые числовые данные некорректны
     * @throws IllegalArgumentException если введённые перечисления (weaponType, meleeWeapon) некорректны
     */
    @Override
    public void execute(String[] args) {
        Vector<SpaceMarine> collection = collectionManager.getCollection();
        Scanner scanner = new Scanner(System.in);
        SpaceMarine.Builder builder = new SpaceMarine.Builder();

        if (args.length == 0) {
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
        } else {
            // Вариант 2: Прямой ввод всех полей через запятую
            if (args.length != 11) {
                System.out.println("Ошибка: для add с аргументами требуется одна строка с 11 полями, разделёнными запятыми: " +
                        "name, x, y, health, achievements, weaponType, meleeWeapon, chapterName, parentLegion, marinesCount, world");
                return;
            }
            String argLine = args[0].trim();
            String[] csvArgs = parseCsvArgs(argLine);
            if (csvArgs.length != 11) {
                System.out.println("Ошибка: для add с аргументами требуется 11 полей, разделённых запятыми: " +
                        "name, x, y, health, achievements, weaponType, meleeWeapon, chapterName, parentLegion, marinesCount, world");
                System.out.println("Передано аргументов: " + csvArgs.length);
                return;
            }
            try {
                builder.setName(csvArgs[0].trim());
                builder.setCoordinates(new Coordinates(Integer.parseInt(csvArgs[1].trim()), Double.parseDouble(csvArgs[2].trim())));
                builder.setHealth(Float.parseFloat(csvArgs[3].trim()));
                builder.setAchievements(csvArgs[4].trim());
                builder.setWeaponType(Weapon.valueOf(csvArgs[5].trim().toUpperCase()));
                builder.setMeleeWeapon(MeleeWeapon.valueOf(csvArgs[6].trim().toUpperCase()));
                builder.setChapter(new Chapter(csvArgs[7].trim(), csvArgs[8].trim(), Integer.parseInt(csvArgs[9].trim()), csvArgs[10].trim()));
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: некорректный формат числа: " + e.getMessage());
                return;
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: некорректное значение перечисления: " + e.getMessage());
                return;
            }
        }
        int newId = SpaceMarine.generateNextId(collection);
        builder.setId(newId);

        // Создаём SpaceMarine
        SpaceMarine marine = builder.build();

        // Добавляем в коллекцию
        collection.add(marine);
        System.out.println("Элемент добавлен с id " + newId + ": " + marine);
    }

    /**
     * Возвращает название команды.
     *
     * @return Название команды ("add").
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Возвращает описание команды.
     *
     * @return Описание команды.
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Парсит строку аргументов в формате CSV, учитывая кавычки.
     *
     * @param argLine Строка аргументов, разделённых запятыми, с возможными кавычками.
     * @return Массив строк, представляющих поля из CSV.
     */
    private String[] parseCsvArgs(String argLine) {
        List<String> args = new ArrayList<>();
        StringBuilder currentArg = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < argLine.length(); i++) {
            char c = argLine.charAt(i);
            if (c == '"') {
                inQuotes = !inQuotes;
                continue;
            }
            if (c == ',' && !inQuotes) {
                if (currentArg.length() > 0) {
                    args.add(currentArg.toString().trim());
                    currentArg = new StringBuilder();
                }
                continue;
            }
            currentArg.append(c);
        }
        if (currentArg.length() > 0) {
            args.add(currentArg.toString().trim());
        }

        return args.toArray(new String[0]);
    }
}