package Commands.CommandInterface;

public interface CommandInterface {
    void execute(String[] args); // Выполняет команду с аргументами
    String getDescription();     // Возвращает описание команды
    String getName();
}
