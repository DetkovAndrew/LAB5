package CollectionManager;

import SpaceMarine.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Vector;

public class CollectionManager {
    private Vector<SpaceMarine> collection = new Vector<>();
    private String filePath;

    public CollectionManager() {
        filePath = System.getenv("SPACE_MARINES_FILE");
        if (filePath == null) {
            System.err.println("Переменная SPACE_MARINES_FILE не задана. Используется data.csv");
            filePath = "data.csv"; // Файл по умолчанию
        }
        loadCollection();
    }

    private void loadCollection() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                SpaceMarine marine = new SpaceMarine.Builder()
                        .setId(Integer.parseInt(data[0]))
                        .setName(data[1])
                        .setCoordinates(new Coordinates(Integer.parseInt(data[2]), Double.parseDouble(data[3])))
                        .setHealth(Float.parseFloat(data[4]))
                        .setAchievements(data[5])
                        .setWeaponType(Weapon.valueOf(data[6]))
                        .setMeleeWeapon(MeleeWeapon.valueOf(data[7]))
                        .setChapter(new Chapter(data[8], data[9], Integer.parseInt(data[10]), data[11]))
                        .build();
                collection.add(marine);
            }
            Collections.sort(collection);
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
    }

    public Vector<SpaceMarine> getCollection() {
        return collection;
    }
}
