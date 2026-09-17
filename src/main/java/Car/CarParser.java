package Car;

import Collection.CustomArrayList;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class CarParser {
    public static void writeJSON(Path pathDestination, List<Car> fromList) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            mapper.writeValue(pathDestination.toFile(), fromList);
        } catch (IOException e) {
            throw new IOException("Couldn't write JSON to " + pathDestination.toString() + "\n" + e.getMessage(), e);
        }
    }

    public static void readJSON(Path pathFrom, List<Car> dest, boolean clearDest) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        CustomArrayList<Car> loaded;

        try {
            loaded = mapper.readValue(pathFrom.toFile(), new TypeReference<CustomArrayList<Car>>() {});

            if (clearDest) {
                dest.clear();
            }

        }
        catch (IOException e) {
            throw new IOException(e.getMessage(), e);
        }

        dest.addAll(loaded);

    }

    public static void readFromConsole(int count, List<Car> dest, boolean clearDest) {
        Scanner scanner = new Scanner(System.in);

        List<Car> temp;

        try {
            temp = IntStream.range(0, count)
                    .mapToObj(i -> readCarFromConsole(scanner, i))
                    .toList();

        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }

        if (clearDest) {
            dest.clear();
        }

        dest.addAll(temp);

    }


    private static Car readCarFromConsole(Scanner scanner, int carNumber) {

        System.out.println("\n--- Машина №" + (carNumber + 1) + " ---");

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

        System.out.println("Машина добавлена!");

        return carBuilder.build();
    }
}

