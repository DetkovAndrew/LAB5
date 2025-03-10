package CollectionManager;

import SpaceMarine.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Vector;

/**
 * Класс CollectionManager управляет коллекцией объектов SpaceMarine.
 * Отвечает за загрузку данных из файла, хранение коллекции и предоставление информации о ней.
 * Коллекция инициализируется из файла, указанного в переменной окружения SPACE_MARINES_FILE.
 *
 * @author Андрей
 * @version 1.0
 * @since 2025-03-10
 */
public class CollectionManager {
    /** Коллекция объектов SpaceMarine. */
    private final Vector<SpaceMarine> collection = new Vector<>();

    /** Дата и время инициализации коллекции. */
    private final LocalDateTime initializationDate;

    /** Путь к файлу, из которого загружается коллекция. */
    private String filePath;

    /**
     * Конструктор класса CollectionManager.
     * Инициализирует коллекцию, устанавливает дату инициализации и загружает данные из файла.
     * Если переменная окружения SPACE_MARINES_FILE не задана, используется файл по умолчанию "data.csv".
     */
    public CollectionManager() {
        this.initializationDate = LocalDateTime.now();
        filePath = System.getenv("SPACE_MARINES_FILE");
        if (filePath == null) {
            System.err.println("Переменная SPACE_MARINES_FILE не задана. Используется data.csv");
            filePath = "data.csv"; // Файл по умолчанию
        }
        loadCollection();
    }

    /**
     * Загружает коллекцию из файла, указанного в переменной filePath.
     * Каждая строка файла должна содержать данные объекта SpaceMarine в формате CSV.
     * Пропускает строки с некорректным форматом или дублирующимися id.
     *
     * @throws IOException если произошла ошибка при чтении файла
     */
    private void loadCollection() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    try {
                        String[] data = line.split(",", -1); // -1 чтобы сохранить пустые поля
                        if (data.length != 13) { // Ожидаем 13 полей (id, name, x, y, creationDate, health, achievements, weaponType, meleeWeapon, chapterName, parentLegion, marinesCount, world)
                            System.out.println("Пропущена строка: неверный формат (" + line + ")");
                            continue;
                        }
                        // Парсим id из CSV
                        int id = Integer.parseInt(data[0]);
                        // Проверяем уникальность id
                        if (collection.stream().anyMatch(marine -> marine.getId() != null && marine.getId().equals(id))) {
                            System.out.println("Пропущена строка: id " + id + " уже существует (" + line + ")");
                            continue;
                        }
                        // Парсим остальные поля
                        String name = data[1];
                        int x = Integer.parseInt(data[2]);
                        double y = Double.parseDouble(data[3]);
                        LocalDateTime creationDate = LocalDateTime.parse(data[4]);
                        float health = Float.parseFloat(data[5]);
                        String achievements = data[6];
                        Weapon weaponType = Weapon.valueOf(data[7]);
                        MeleeWeapon meleeWeapon = MeleeWeapon.valueOf(data[8]);
                        String chapterName = data[9];
                        String parentLegion = data[10];
                        int marinesCount = Integer.parseInt(data[11]);
                        String world = data[12];

                        // Создаём объект через Builder
                        SpaceMarine marine = new SpaceMarine.Builder()
                                .setId(id)
                                .setName(name)
                                .setCoordinates(new Coordinates(x, y))
                                .setCreationDate(creationDate)
                                .setHealth(health)
                                .setAchievements(achievements)
                                .setWeaponType(weaponType)
                                .setMeleeWeapon(meleeWeapon)
                                .setChapter(new Chapter(chapterName, parentLegion, marinesCount, world))
                                .build();

                        collection.add(marine);
                    } catch (IllegalArgumentException | java.time.format.DateTimeParseException e) {
                        System.out.println("Ошибка при парсинге строки: " + line + " (" + e.getMessage() + ")");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
    }

    /**
     * Возвращает коллекцию объектов SpaceMarine.
     *
     * @return Коллекция объектов SpaceMarine.
     */
    public Vector<SpaceMarine> getCollection() {
        return collection;
    }

    /**
     * Возвращает дату и время инициализации коллекции.
     *
     * @return Дата и время инициализации коллекции.
     */
    public LocalDateTime getInitializationDate() {
        return initializationDate;
    }
}
