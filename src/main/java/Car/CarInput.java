package Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarInput {

    public static void write(int count, List<Car> dest, boolean clearDest) {
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
