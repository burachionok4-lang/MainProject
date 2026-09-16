package IO;

import Car.Car;
import java.util.List;
import java.util.Scanner;

public class ConsoleIO {
    static public final int DEFAULT_PRINT_LIST_LIMIT = 10;

    static public int printListLimit = DEFAULT_PRINT_LIST_LIMIT;

    private static Scanner scanner = new Scanner(System.in);

    public static void printMainMenu() {
        System.out.println("=========================================\n" +
                "         ПРИЛОЖЕНИЕ СОРТИРОВКИ \n" +
                "=========================================");

        System.out.println("1. Заполнить массив и выполнить сортировку");
        System.out.println("2. Выход из программы");

        System.out.print("Выберите действие (1 или 2): ");
    }

    public static void printFillOptions() {
        System.out.println("\n--- Выберите способ заполнения массива ---");

        System.out.println("1. Случайная генерация (рандом)");
        System.out.println("2. Ручной ввод с клавиатуры");
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
                System.out.println("Ручной ввод данных:");
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

        stream.forEach(System.out::println);

        if (carList.size() - 1 > limit) {
            System.out.println("и еще " + (carList.size() - 1 - limit) + " элементов...");
        }
    }

    public static void printSortingOptions() {
        System.out.println("\n--- Сортировка ---");
        System.out.println("1. Модель");
        System.out.println("2. Мощность");
        System.out.println("3. Год");
        System.out.println("4. По всем полям");
        System.out.print("Ваш выбор: ");
    }


    public static void waitAnyInput()
    {
        scanner.nextLine();
    }

    public static void scanNextInt()
    {
        int mainSelectionInt = scanner.nextInt();


        scanner.nextLine();
    }

    public static int checkIntInput(Scanner scanner, int min, int max)
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
}
