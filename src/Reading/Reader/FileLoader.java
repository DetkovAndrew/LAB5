package Reading.Reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Vector;

import SpaceMarine.*;

public class FileLoader {
    public static Vector<SpaceMarine> loadSpaceMarines() {
        String fileName = System.getenv("SPACE_MARINES_FILE"); // Получаем имя файла
        if (fileName == null) {
            throw new RuntimeException("Ошибка: Переменная окружения SPACE_MARINES_FILE не задана.");
        }

        Vector<SpaceMarine> marines = new Vector<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
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
                marines.add(marine);
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
        Collections.sort(marines);
        return marines;
    }
}

