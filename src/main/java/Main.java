import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Car.*;
import IO.*;
import Sorter.*;

/*private static void runSortingProcess(Scanner scanner) {
    // Шаг 1: Выбор заполнения
    System.out.println("\n--- Выберите способ заполнения массива ---");
    System.out.println("1. Случайная генерация (рандом)");
    System.out.println("2. Ручной ввод с клавиатуры");
    System.out.println("3. Загрузка из файла");
    System.out.print("Ваш выбор: ");
    int fillChoice = scanner.nextInt();
    scanner.nextLine();

    // Запрашиваем длину (для рандома и ручного ввода, для файла можно игнорировать)
    System.out.print("Введите длину массива: ");
    int length = scanner.nextInt();
    scanner.nextLine();
    switch (fillChoice) {
        case 1 -> {
            System.out.println("Генерация случайных данных...");
//                array = RandomFiller.fill(length);
        }
        case 2 -> {
            System.out.println("Ручной ввод данных:");
//                array = ManualFiller.fill(length, scanner);
        }
        case 3 -> {
            System.out.print("Введите путь к файлу (например, data.txt): ");
            String filePath = scanner.nextLine();
//                array = FileFiller.fill(filePath);
        }
        default -> {
            System.out.println("Неверный выбор. Возврат в главное меню.");
            return;
        }
//            if (array == null || array.length == 0) {
//                System.out.println("Не удалось заполнить массив.");
//                return;
//            }
    }*/

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Car> cars = new ArrayList<>();     //Общий список на все виды ввода
        while (true) {
            ConsoleIO.printMainMenu();

            //TODO: валидировать, мб заменить на метод
            int mainChoice = scanner.nextInt();
            scanner.nextLine();
            switch (mainChoice) {
                case 1 -> {
                    ConsoleIO.printFillOptions();

                    //TODO: валидировать, мб заменить на метод
                    int fillChoice = scanner.nextInt();
                    scanner.nextLine();

                    ConsoleIO.printFillSelection(fillChoice);
                    switch (fillChoice) {
                        case 1 -> {

                        }
                        case 2 -> {
                            System.out.println("Введите количество машин:");
                            int count = scanner.nextInt();
                            scanner.nextLine();
                            CarInput.write(count,cars,false);



                        }
                        case 3 -> {
                            //TODO: заглянуть в билдер и продумать валидацию получше
                            String filePath = scanner.nextLine();
                            CarParser.read(Paths.get(filePath), cars, false);
                        }
                    }

//src/data.json
                    if (!cars.isEmpty()) {
                        System.out.println("\nВведённые машины:");
                        cars.forEach(System.out::println);
                    } else {
                        System.out.println("Список пуст.");
                    }
                    List<Car> carList = new ArrayList<>();

                    Sorter.sort(carList);
                }
                case 2 -> {
                    ConsoleIO.printExit();
                    scanner.close();
                    return;
                }
                default ->
                    System.out.println("Ошибка: введите 1 или 2.");

            }
        }
    }
}