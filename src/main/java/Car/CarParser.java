package Car;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class CarParser {
    public static void writeJSON(Path pathDestination, List<Car> fromList) {
        ObjectMapper mapper = new ObjectMapper();

        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            mapper.writeValue(pathDestination.toFile(), fromList);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't write JSON to " + pathDestination.toString() + "\n" + e.getMessage(), e);
        }
    }

    public static void readJSON(Path pathFrom, List<Car> dest, boolean clearDest) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            List<Car> loaded = mapper.readValue(pathFrom.toFile(), new TypeReference<List<Car>>() {});

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

            Car.Builder carBuilder = new Car.Builder();

            while (true) {
                System.out.print("Модель: ");

                try {
                    String model = scanner.nextLine().trim();

                    carBuilder.setModel(model);

                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Ошибка: " + e.getMessage());
                }
            }

            while (true) {
                System.out.print("Мощность (л.с.): ");

                String line = scanner.nextLine().trim().replace(',', '.');

                try {
                    double power = Double.parseDouble(line);

                    carBuilder.setPower(power);

                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: введите число (например, 150 или 150.5).");
                } catch (IllegalArgumentException e) {
                    System.out.println("Ошибка: " + e.getMessage());
                }
            }

            while (true) {
                System.out.print("Год выпуска: ");

                String line = scanner.nextLine().trim();

                try {
                    int year = Integer.parseInt(line);

                    carBuilder.setYear(year);

                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: введите целое число (например, 2020).");
                } catch (IllegalArgumentException e) {
                    System.out.println("Ошибка: " + e.getMessage());
                }
            }

            dest.add(carBuilder.build());

            System.out.println("Машина добавлена!");
        }
    }

}

