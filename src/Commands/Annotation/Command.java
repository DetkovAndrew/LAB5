package Commands.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Указываем, что аннотация применяется к типам (классам, интерфейсам)
@Target(ElementType.TYPE)
// Указываем, что аннотация доступна во время выполнения
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
    // Параметр для имени команды
    String name();
}
