package SpaceMarine;

public class Coordinates {
    private Integer x; //Поле не может быть null
    private double y;

    public Coordinates(Integer x, double y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() { return x; }
    public Double getY() { return y; }
}
