package SpaceMarine;

public class Chapter {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String parentLegion;
    private Integer marinesCount; //Поле может быть null, Значение поля должно быть больше 0, Максимальное значение поля: 1000
    private String world; //Поле не может быть null

    public Chapter(String name, String parentLegion, Integer marinesCount, String world) {
        this.name = name;
        this.parentLegion = parentLegion;
        this.marinesCount = marinesCount;
        this.world = world;
    }
}
