package SpaceMarine;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Vector;

/**
 * Класс SpaceMarine представляет космического десантника с различными характеристиками.
 * Реализует интерфейс Comparable для сравнения объектов по id.
 * Использует паттерн Builder для создания экземпляров с валидацией полей.
 *
 * @author Андрей
 * @version 1.0
 * @since 2025-03-10
 */
public class SpaceMarine implements Comparable<SpaceMarine> {
    /** Идентификатор десантника. Поле не может быть null, значение должно быть больше 0, уникально и генерироваться автоматически. */
    private Integer id;

    /** Имя десантника. Поле не может быть null, строка не может быть пустой. */
    private final String name;

    /** Координаты десантника. Поле не может быть null. */
    private Coordinates coordinates;

    /** Дата создания десантника. Поле не может быть null, значение генерируется автоматически. */
    private final LocalDateTime creationDate;

    /** Здоровье десантника. Значение должно быть больше 0. */
    private float health;

    /** Достижения десантника. Поле не может быть null. */
    private String achievements;

    /** Тип оружия десантника. Поле может быть null. */
    private Weapon weaponType;

    /** Тип ближнего оружия десантника. Поле не может быть null. */
    private MeleeWeapon meleeWeapon;

    /** Глава десантника. Поле не может быть null. */
    private Chapter chapter;

    /** Минимальное значение для генерации id. */
    private static final int MIN_ID = 100000;

    /** Максимальное значение для генерации id. */
    private static final int MAX_ID = 999999;

    /** Дата и время, связанная с объектом (используется для демонстрации). */
    public LocalDateTime date = LocalDateTime.now();

    /**
     * Конструктор класса SpaceMarine с использованием Builder.
     *
     * @param builder Объект Builder, содержащий все параметры для создания SpaceMarine.
     */
    public SpaceMarine(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.coordinates = builder.coordinates;
        this.creationDate = builder.creationDate;
        this.health = builder.health;
        this.achievements = builder.achievements;
        this.weaponType = builder.weaponType;
        this.meleeWeapon = builder.meleeWeapon;
        this.chapter = builder.chapter;
    }

    /**
     * Генерирует уникальный идентификатор для нового объекта SpaceMarine.
     *
     * @param collection Коллекция, в которой проверяется уникальность id.
     * @return Уникальный id в диапазоне от MIN_ID до MAX_ID.
     * @throws IllegalStateException если не удалось найти уникальный id после максимального числа попыток
     */
    public static int generateNextId(Vector<SpaceMarine> collection) {
        Random random = new Random();
        int maxAttempts = (MAX_ID - MIN_ID) + 1; // Максимум 900000 попыток

        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            int randomId = random.nextInt(MAX_ID - MIN_ID + 1) + MIN_ID; // Случайное число от 100000 до 999999
            boolean isIdTaken = collection.stream().anyMatch(marine -> marine.getId() != null && marine.getId().equals(randomId));
            if (!isIdTaken) {
                return randomId;
            }
        }

        throw new IllegalStateException("Не удалось найти уникальный id в диапазоне от " + MIN_ID + " до " + MAX_ID);
    }

    /**
     * Сравнивает текущий объект SpaceMarine с другим по идентификатору.
     *
     * @param other Другой объект SpaceMarine для сравнения.
     * @return Отрицательное число, если текущий id меньше, 0 если равны, положительное если больше.
     */
    @Override
    public int compareTo(SpaceMarine other) {
        return Integer.compare(this.id, other.id);
    }

    /**
     * Внутренний класс Builder для пошагового создания объекта SpaceMarine с валидацией.
     */
    public static class Builder {
        private Integer id;
        private String name;
        private Coordinates coordinates;
        private LocalDateTime creationDate;
        private float health;
        private String achievements;
        private Weapon weaponType;
        private MeleeWeapon meleeWeapon;
        private Chapter chapter;

        /**
         * Устанавливает идентификатор.
         *
         * @param id Идентификатор (должен быть больше 0).
         * @return Текущий объект Builder.
         * @throws IllegalArgumentException если id null или меньше/равно 0
         */
        public Builder setId(Integer id) {
            if (id == null || id <= 0) throw new IllegalArgumentException("ID должно быть больше 0");
            this.id = id;
            return this;
        }

        /**
         * Устанавливает имя.
         *
         * @param name Имя (не может быть null или пустым).
         * @return Текущий объект Builder.
         * @throws IllegalArgumentException если имя null или пустое
         */
        public Builder setName(String name) {
            if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Имя не может быть пустым");
            this.name = name;
            return this;
        }

        /**
         * Устанавливает координаты.
         *
         * @param coordinates Координаты (не могут быть null).
         * @return Текущий объект Builder.
         * @throws IllegalArgumentException если координаты null
         */
        public Builder setCoordinates(Coordinates coordinates) {
            if (coordinates == null) throw new IllegalArgumentException("Координаты не могут быть null");
            this.coordinates = coordinates;
            return this;
        }

        /**
         * Устанавливает дату создания.
         *
         * @param creationDate Дата создания (не может быть null).
         * @return Текущий объект Builder.
         * @throws IllegalArgumentException если дата null
         */
        public Builder setCreationDate(LocalDateTime creationDate) {
            if (creationDate == null) throw new IllegalArgumentException("Дата создания не может быть null");
            this.creationDate = creationDate;
            return this;
        }

        /**
         * Устанавливает здоровье.
         *
         * @param health Здоровье (должно быть больше 0).
         * @return Текущий объект Builder.
         * @throws IllegalArgumentException если здоровье меньше/равно 0
         */
        public Builder setHealth(float health) {
            if (health <= 0) throw new IllegalArgumentException("Здоровье должно быть больше 0");
            this.health = health;
            return this;
        }

        /**
         * Устанавливает достижения.
         *
         * @param achievements Достижения (не могут быть null).
         * @return Текущий объект Builder.
         * @throws IllegalArgumentException если достижения null
         */
        public Builder setAchievements(String achievements) {
            if (achievements == null) throw new IllegalArgumentException("Достижения не могут быть null");
            this.achievements = achievements;
            return this;
        }

        /**
         * Устанавливает тип оружия.
         *
         * @param weaponType Тип оружия (может быть null).
         * @return Текущий объект Builder.
         */
        public Builder setWeaponType(Weapon weaponType) {
            this.weaponType = weaponType; // Оружие может быть null
            return this;
        }

        /**
         * Устанавливает тип ближнего оружия.
         *
         * @param meleeWeapon Тип ближнего оружия (не может быть null).
         * @return Текущий объект Builder.
         * @throws IllegalArgumentException если ближнее оружие null
         */
        public Builder setMeleeWeapon(MeleeWeapon meleeWeapon) {
            if (meleeWeapon == null) throw new IllegalArgumentException("Ближнее оружие не может быть null");
            this.meleeWeapon = meleeWeapon;
            return this;
        }

        /**
         * Устанавливает главу.
         *
         * @param chapter Глава (не может быть null).
         * @return Текущий объект Builder.
         * @throws IllegalArgumentException если глава null
         */
        public Builder setChapter(Chapter chapter) {
            if (chapter == null) throw new IllegalArgumentException("Chapter не может быть null");
            this.chapter = chapter;
            return this;
        }

        /**
         * Создаёт объект SpaceMarine на основе настроек Builder.
         * Если id или creationDate не установлены, генерирует их автоматически.
         *
         * @return Новый объект SpaceMarine.
         * @throws IllegalStateException если id не установлен
         */
        public SpaceMarine build() {
            if (this.id == null) throw new IllegalStateException("ID не установлен");
            if (this.creationDate == null) this.creationDate = LocalDateTime.now(); // Генерация даты автоматически
            return new SpaceMarine(this);
        }
    }

    /**
     * Создаёт объект SpaceMarine из строки в формате CSV.
     *
     * @param csv Строка в формате CSV (id,name,x,y,creationDate,health,achievements,weaponType,meleeWeapon,chapterName,parentLegion,marinesCount,world).
     * @return Новый объект SpaceMarine.
     * @throws IllegalArgumentException если данные в CSV некорректны
     */
    public static SpaceMarine fromCSV(String csv) {
        String[] data = csv.split(",");
        return new Builder()
                .setName(data[0])
                .setCoordinates(new Coordinates(Integer.parseInt(data[1]), Double.parseDouble(data[2])))
                .setCreationDate(LocalDateTime.parse(data[3]))
                .setHealth(Float.parseFloat(data[4]))
                .setAchievements(data[5])
                .setWeaponType(Weapon.valueOf(data[6]))
                .setMeleeWeapon(MeleeWeapon.valueOf(data[7]))
                .setChapter(new Chapter(data[8], data[9], Integer.parseInt(data[10]), data[11]))
                .build();
    }

    /**
     * Преобразует объект SpaceMarine в строку в формате CSV.
     *
     * @return Строка в формате CSV (id,name,x,y,creationDate,health,achievements,weaponType,meleeWeapon,chapterName,parentLegion,marinesCount,world).
     */
    public String toCSV() {
        return String.format("%d,%s,%d,%.1f,%s,%.1f,%s,%s,%s,%s,%s,%d,%s",
                id,
                name,
                coordinates.getX(), coordinates.getY(),
                creationDate.format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                health,
                achievements,
                weaponType, meleeWeapon,
                chapter.getName(),
                chapter.getParentLegion(),
                chapter.getMarinesCount(),
                chapter.getWorld());
    }

    /**
     * Возвращает идентификатор десантника.
     *
     * @return Идентификатор (id).
     */
    public Integer getId() {
        return id;
    }

    /**
     * Возвращает имя десантника.
     *
     * @return Имя (name).
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает координаты десантника.
     *
     * @return Координаты (coordinates).
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Возвращает дату создания десантника.
     *
     * @return Дата создания (creationDate).
     */
    public java.time.LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Возвращает здоровье десантника.
     *
     * @return Здоровье (health).
     */
    public float getHealth() {
        return health;
    }

    /**
     * Возвращает достижения десантника.
     *
     * @return Достижения (achievements).
     */
    public String getAchievements() {
        return achievements;
    }

    /**
     * Возвращает тип оружия десантника.
     *
     * @return Тип оружия (weaponType).
     */
    public Weapon getWeaponType() {
        return weaponType;
    }

    /**
     * Возвращает тип ближнего оружия десантника.
     *
     * @return Тип ближнего оружия (meleeWeapon).
     */
    public MeleeWeapon getMeleeWeapon() {
        return meleeWeapon;
    }

    /**
     * Возвращает главу десантника.
     *
     * @return Глава (chapter).
     */
    public Chapter getChapter() {
        return chapter;
    }

    /**
     * Устанавливает идентификатор десантника.
     *
     * @param id Новый идентификатор (должен быть больше 0).
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Возвращает строковое представление объекта SpaceMarine.
     *
     * @return Строковое представление объекта с всеми полями.
     */
    @Override
    public String toString() {
        return "SpaceMarine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + (coordinates != null ? coordinates.getX() + ", " + coordinates.getY() : "null") +
                ", creationDate=" + (creationDate != null ? creationDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) : "null") +
                ", health=" + health +
                ", achievements='" + achievements + '\'' +
                ", weaponType=" + weaponType +
                ", meleeWeapon=" + meleeWeapon +
                ", chapter=" + (chapter != null ? "Chapter{name='" + chapter.getName() + "', parentLegion='" + chapter.getParentLegion() +
                "', marinesCount=" + chapter.getMarinesCount() + ", world='" + chapter.getWorld() + "'}" : "null") +
                '}';
    }
}