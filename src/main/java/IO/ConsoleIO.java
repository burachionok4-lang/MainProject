package IO;

import Car.Car;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class ConsoleIO {
    static public final int DEFAULT_PRINT_LIST_LIMIT = 10;

    static public int printListLimit = DEFAULT_PRINT_LIST_LIMIT;

    private static Scanner scanner = new Scanner(System.in);

    public static void printMainMenu() {
        System.out.println("=========================================\n" +
                "         ПРИЛОЖЕНИЕ СОРТИРОВКИ \n" +
                "=========================================");

        System.out.println("1. Заполнить массив и выполнить сортировку");
        System.out.println("2. Показывать элементы: " + printListLimit);
        System.out.println("3. Выход из программы");
        //System.out.print("\n");
    }

    public static void printFillOptions() {
        System.out.println("\n--- Выберите способ заполнения списка ---");

        System.out.println("1. Случайная генерация (рандом)");
        System.out.println("2. Ручной ввод");
        System.out.println("3. Загрузка из .json файла");

        System.out.print("Ваш выбор: ");
    }

    public static void printExit() {
        System.out.println("\nПрограмма завершена. До свидания!");
    }

    public static void printFillSelection(FillSelectionOption selection) {
        switch (selection) {
            case RANDOM -> {
                System.out.println("Случайное заполнение");
            }
            case MANUAL_INPUT -> {
                System.out.println("Ручной ввод:");
            }
            case FROM_FILE -> {
                System.out.print("Введите путь к файлу (напр. test.json): ");
            }
            default -> {
                System.out.println("Неверный выбор");
            }
        }
    }

    //@param limit how many to print, 0 is unlimited
    public static void printCarList(List<Car> carList, int limit) {

        var stream = carList.stream();

        if (limit > 0) {
            stream = stream.limit(limit);
        }

        System.out.println("  Модель  Мощность  Год");

        AtomicInteger counter = new AtomicInteger(1);

        stream.forEach((Car car) -> {
            System.out.println(counter.getAndIncrement() + ". " + car.getModel() + ", " + car.getPower() + ", " + car.getYear());
        });

        if (carList.size() > limit) {
            System.out.println("и еще " + (carList.size() - limit) + " элементов...");
        }
    }

    public static void printSortingOptions() {
        System.out.println("\n--- Сортировка ---");
        System.out.println("1. Модель");
        System.out.println("2. Мощность");
        System.out.println("3. Год");
        System.out.println("4. По всем полям");
        System.out.print("\n");
        System.out.println("5. Выйти");

        System.out.print("Ваш выбор: ");
    }

    public static void changePrintLimit(){
        System.out.print("Новое значение: ");

        ConsoleIO.printListLimit = ConsoleIO.checkedIntInput(scanner, 1, 100000);
    }

    public static int checkedIntInput(Scanner scanner, int min, int max)
   {
       while (true) {
           String line = scanner.nextLine().trim();

           try {
               int value = Integer.parseInt(line);

               if (value < min || value > max) {
                   System.out.printf("Ошибка: число должно быть в диапазоне [%d, %d].%n", min, max);

                   continue;
               }

               return value;
           } catch (NumberFormatException e) {
               System.out.println("Ошибка: введите целое число.");
           }
       }
   }

    public static double checkedDoubleInput(Scanner scanner, double min, double max)
    {
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(line);

                if (value < min || value > max) {
                    System.out.printf("Ошибка: число должно быть в диапазоне [%f, %f].%n", min, max);

                    continue;
                }

                return value;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число с плавающей точкой.");
            }
        }
    }
}
