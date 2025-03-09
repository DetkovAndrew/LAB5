package Commands.ColllectionOperating;

import Commands.CommandInterface.CommandInterface;
import CollectionManager.CollectionManager;

import java.time.format.DateTimeFormatter;
import java.util.Vector;

public class Info implements CommandInterface {
    private final String name = "info";
    private final String description = "вывести информацию о коллекции";
    private final CollectionManager collectionManager;

    public Info(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        System.out.println("Информация о коллекции:");
        System.out.println("Тип: " + collectionManager.getCollection().getClass().getSimpleName());
        System.out.println("Дата инициализации: " +
                collectionManager.getInitializationDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        System.out.println("Количество элементов: " + collectionManager.getCollection().size());
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
