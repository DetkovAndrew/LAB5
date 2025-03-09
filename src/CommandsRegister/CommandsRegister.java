package CommandsRegister;

import Commands.Annotation.Command;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CommandsRegister {
    private Map<String, Runnable> commandMap = new HashMap<>();

    public CommandsRegister() {
        // Список пакетов для сканирования
        String[] packages = {
                "Commands.CollectionOperating",
                "Commands.ElementsOuterOperating",
                "Commands.GeneralCommands",
                "Commands.ElementsInnerOperating",
                "Commands.ScriptOperating"
        };

        // Сканируем каждый пакет
        for (String pkg : packages) {
            Reflections reflections = new Reflections(pkg);
            Set<Class<?>> commandClasses = reflections.getTypesAnnotatedWith(Command.class);

            // Регистрируем найденные команды
            for (Class<?> clazz : commandClasses) {
                Command annotation = clazz.getAnnotation(Command.class);
                String name = annotation.name();
                try {
                    Runnable instance = (Runnable) clazz.getDeclaredConstructor().newInstance();
                    commandMap.put(name, instance);
                } catch (Exception e) {
                    System.err.println("Ошибка при создании команды " + name + ": " + e.getMessage());
                }
            }
        }
    }
}
