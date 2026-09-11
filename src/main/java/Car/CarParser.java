package Car;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarParser {
    public static void writeJSON(Path pathDestination, List<Car> fromList, boolean clearDest) {

    }

    public static void readJSON(Path pathFrom, List<Car> dest, boolean clearDest) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            List<Car> loaded = mapper.readValue(
                    pathFrom.toFile(),
                    new TypeReference<List<Car>>() {}
            );


            if (clearDest) {
                dest.clear();
            }

            dest.addAll(loaded);

        } catch (IOException e) {
            throw new RuntimeException("Couldn't read JSON from " + pathFrom.toString() + "\n" + e.getMessage(), e);
        }
    }

    public static void readFromConsole(int count, List<Car> dest, boolean clearDest) {
        Scanner scanner = new Scanner(System.in);

        if (clearDest) {
            dest.clear();
        }
        for (int i = 0; i < count; i++) {
            System.out.println("\n--- Машина №" + (i + 1) + " ---");

            System.out.print("Модель: ");

            String model = scanner.nextLine();

            System.out.print("Мощность (л.с.): ");

            double power = scanner.nextDouble();

            scanner.nextLine();

            System.out.print("Год выпуска: ");

            int year = scanner.nextInt();

            scanner.nextLine();

            Car car = new Car.Builder()
                    .setModel(model)
                    .setPower(power)
                    .setYear(year)
                    .build();
            dest.add(car);
        }
    }

}
