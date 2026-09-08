package Car;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CarParser {
    public static void write(Path pathDestination, List<Car> fromList, boolean clearDest) {

    }

    public static void read(Path pathFrom, List<Car> dest, boolean clearDest) {
        try {
            // 1. Читаем все машины из файла во временный список
            ObjectMapper mapper = new ObjectMapper();
            List<Car> loaded = mapper.readValue(
                    pathFrom.toFile(),
                    new TypeReference<List<Car>>() {}
            );

            // 2. Если нужно, очищаем целевой список
            if (clearDest) {
                dest.clear();
            }

            // 3. Добавляем загруженные объекты
            dest.addAll(loaded);
        } catch (IOException e) {
            // В случае ошибки выбрасываем исключение, чтобы вызывающий код мог обработать
            throw new RuntimeException("Ошибка чтения файла: " + pathFrom, e);
        }
    }

}
