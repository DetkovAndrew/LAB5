package Commands.ColllectionOperating;

import Commands.CommandInterface.CommandInterface;
import SpaceMarine.SpaceMarine;
import CollectionManager.CollectionManager;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class Save implements CommandInterface {
    private final String name = "save";
    private final String description = "сохранить коллекцию в файл";
    private final CollectionManager collectionManager;
    private String filePath;

    public Save(CollectionManager collectionManager, String filePath) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        Vector<SpaceMarine> collection = collectionManager.getCollection();
        String filePath = System.getenv("SPACE_MARINES_FILE") != null ? System.getenv("SPACE_MARINES_FILE") : "SPACE_MARINES_FILE.csv";
        try (FileWriter writer = new FileWriter(filePath)) {
            for (SpaceMarine marine : collection) {
                writer.write(marine.toCSV() + "\n");
            }
            System.out.println("Коллекция сохранена в файл: " + filePath);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении: " + e.getMessage());
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
