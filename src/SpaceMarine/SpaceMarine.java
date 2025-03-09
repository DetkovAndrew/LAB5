package SpaceMarine;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;
import java.util.Vector;

public class SpaceMarine implements Comparable<SpaceMarine>{
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float health; //Значение поля должно быть больше 0
    private String achievements; //Поле не может быть null
    private Weapon weaponType; //Поле может быть null
    private MeleeWeapon meleeWeapon; //Поле не может быть null
    private Chapter chapter; //Поле не может быть null

    private static final int MIN_ID = 100000;
    private static final int MAX_ID = 999999;

    public LocalDateTime date = LocalDateTime.now();

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

    @Override
    public int compareTo(SpaceMarine other) {
        return Integer.compare(this.id, other.id);
    }

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

        public Builder setId(Integer id) {
            if (id == null || id <= 0) throw new IllegalArgumentException("ID должно быть больше 0");
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            if (name == null || name.trim().isEmpty())
                throw new IllegalArgumentException("Имя не может быть пустым");
            this.name = name;
            return this;
        }

        public Builder setCoordinates(Coordinates coordinates) {
            if (coordinates == null) throw new IllegalArgumentException("Координаты не могут быть null");
            this.coordinates = coordinates;
            return this;
        }

        public Builder setCreationDate(LocalDateTime creationDate) {
            if (creationDate == null) throw new IllegalArgumentException("Дата создания не может быть null");
            this.creationDate = creationDate;
            return this;
        }

        public Builder setHealth(float health) {
            if (health <= 0) throw new IllegalArgumentException("Здоровье должно быть больше 0");
            this.health = health;
            return this;
        }

        public Builder setAchievements(String achievements) {
            if (achievements == null) throw new IllegalArgumentException("Достижения не могут быть null");
            this.achievements = achievements;
            return this;
        }

        public Builder setWeaponType(Weapon weaponType) {
            this.weaponType = weaponType; // Оружие может быть null
            return this;
        }

        public Builder setMeleeWeapon(MeleeWeapon meleeWeapon) {
            if (meleeWeapon == null) throw new IllegalArgumentException("Ближнее оружие не может быть null");
            this.meleeWeapon = meleeWeapon;
            return this;
        }

        public Builder setChapter(Chapter chapter) {
            if (chapter == null) throw new IllegalArgumentException("Chapter не может быть null");
            this.chapter = chapter;
            return this;
        }

        public SpaceMarine build() {
            if (this.id == null) throw new IllegalStateException("ID не установлен");
            if (this.creationDate == null) this.creationDate = LocalDateTime.now(); // Генерация даты автоматически
            return new SpaceMarine(this);
        }
    }

    public static SpaceMarine fromCSV(String csv) {
        String[] data = csv.split(",");
        return new Builder()
                .setId(Integer.parseInt(data[0]))
                .setName(data[1])
                .setCoordinates(new Coordinates(Integer.parseInt(data[2]), Double.parseDouble(data[3])))
                .setHealth(Float.parseFloat(data[4]))
                .setAchievements(data[5])
                .setWeaponType(Weapon.valueOf(data[6]))
                .setMeleeWeapon(MeleeWeapon.valueOf(data[7]))
                .setChapter(new Chapter(data[8], data[9], Integer.parseInt(data[10]), data[11]))
                .build();
    }

    public String toCSV() {
        return String.format("%d,%s,%d,%.1f,%.1f,%s,%s,%s,%s,%s,%d,%s",
                id, name,
                coordinates.getX(), coordinates.getY(),
                health,
                achievements,
                weaponType, meleeWeapon,
                chapter.getName(),
                chapter.getParentLegion(),
                chapter.getMarinesCount(),
                chapter.getWorld());
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public java.time.LocalDateTime getCreationDate() {
        return creationDate;
    }

    public float getHealth() {
        return health;
    }

    public String getAchievements() {
        return achievements;
    }

    public Weapon getWeaponType() {
        return weaponType;
    }

    public MeleeWeapon getMeleeWeapon() {
        return meleeWeapon;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}