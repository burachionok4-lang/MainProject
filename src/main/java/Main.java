import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Car.*;
import static Car.Car.of;
import Collection.CustomArrayList;
import IO.*;
import Sorter.*;
import Test.CarParserTest;

class Main {
    public static void main(String[] args) {

        final boolean TEST_MODE = false;

        if(TEST_MODE)
        {
            System.out.println("Режим тестирования");

            IO.println("writeReadJSON: " + CarParserTest.writeReadJSON());

            return;
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            List<Car> filledCars = new CustomArrayList<Car>();
            ConsoleIO.printMainMenu();

            //TODO: валидировать, мб заменить на метод
            int mainSelectionInt = scanner.nextInt();
            scanner.nextLine();

            MainSelectionOption mainSelectionOption = MainSelectionOption.fromInt(mainSelectionInt);

            switch (mainSelectionOption) {
                case FILL_AND_SORT -> {

                    while (true) {
                        ConsoleIO.printFillOptions();
                        //TODO: валидировать, мб заменить на метод
                        int fillSelectionInt = scanner.nextInt();
                        scanner.nextLine();

                        FillSelectionOption fillSelectionOption = FillSelectionOption.fromInt(fillSelectionInt);

                        ConsoleIO.printFillSelection(fillSelectionOption);

                        switch (fillSelectionOption) {
                            case RANDOM -> {
                                //TODO
                                ConsoleIO.randomFill();
                            }
                            case MANUAL_INPUT -> {
                                //TODO

                                System.out.println("Введите количество машин:");

                                int count = scanner.nextInt();
                                scanner.nextLine();

                                CarParser.readFromConsole(count, filledCars, false);
                            }
                            case FROM_FILE -> {
                                String filePath = scanner.nextLine();

                                CarParser.readJSON(Paths.get(filePath), filledCars, false);
                            }
                        }

                        if (!filledCars.isEmpty()) {
                            ConsoleIO.printCarList(filledCars, ConsoleIO.DEFAULT_PRINT_LIST_LIMIT);
                            break;
                        } else {
                            System.out.println("Список пуст, попробуйте снова.");
                        }

                        ConsoleIO.printCarList(filledCars, ConsoleIO.DEFAULT_PRINT_LIST_LIMIT);

                        break;
                    }

                    ConsoleIO.printSortingOptions();
                  
                    int sortChoice = scanner.nextInt();
                    scanner.nextLine();

                    SortSelectionOption sortOption = SortSelectionOption.fromInt(sortChoice);

                    switch (sortOption) {
                        case BY_MODEL -> CarSorter.sort(filledCars, new CarSorter.SortByField<>(Car::getModel));
                        case BY_POWER -> CarSorter.sort(filledCars, new CarSorter.SortByField<>(Car::getPower));
                        case BY_YEAR -> CarSorter.sort(filledCars, new CarSorter.SortByField<>(Car::getYear));
                        case BY_ALL -> CarSorter.sort(filledCars, new CarSorter.SortByAllFields());
                    };

                    System.out.println("\nОтсортированный список:");
                    ConsoleIO.printCarList(filledCars, ConsoleIO.DEFAULT_PRINT_LIST_LIMIT);


                    //scanner.nextLine();
                }
                case EXIT -> {
                    ConsoleIO.printExit();

                    scanner.close();

                    return;
                }
                default -> System.out.println("Нет такого варианта");

            }
        }
    }
}