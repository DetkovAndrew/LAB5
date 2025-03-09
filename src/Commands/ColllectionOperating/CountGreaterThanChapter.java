package Commands.ColllectionOperating;

import Commands.CommandInterface.CommandInterface;
import CollectionManager.CollectionManager;
import SpaceMarine.SpaceMarine;
import SpaceMarine.Chapter;

import java.util.Vector;

public class CountGreaterThanChapter implements CommandInterface {
    private final String name = "count_greater_than_chapter";
    private final String description = "подсчитать элементы с chapter больше заданного";
    private final CollectionManager collectionManager;

    public CountGreaterThanChapter(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Использование: count_greater_than_chapter chapter_name");
            return;
        }
        Vector<SpaceMarine> collection = collectionManager.getCollection();
        String chapterName = args[0];
        Chapter chapter = new Chapter(chapterName, null, 0, null);
        long count = collection.stream()
                .filter(m -> m.getChapter() != null && m.getChapter().getName().compareTo(chapter.getName()) > 0)
                .count();
        System.out.println("Количество элементов с chapter > " + chapterName + ": " + count);
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
