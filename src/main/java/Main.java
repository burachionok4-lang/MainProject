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

            System.out.println("writeReadJSON: " + CarParserTest.writeReadJSON());

            return;
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            List<Car> filledCars = new CustomArrayList<Car>();
            ConsoleIO.printMainMenu();

            int mainSelectionInt = ConsoleIO.checkIntInput(scanner, 1, 2);

            MainSelectionOption mainSelectionOption = MainSelectionOption.fromInt(mainSelectionInt);

            switch (mainSelectionOption) {
                case FILL_AND_SORT -> {

                    while (true) {
                        ConsoleIO.printFillOptions();
                        int fillSelectionInt = ConsoleIO.checkIntInput(scanner, 1, 3);
                        FillSelectionOption fillSelectionOption = FillSelectionOption.fromInt(fillSelectionInt);

                        ConsoleIO.printFillSelection(fillSelectionOption);

                        switch (fillSelectionOption) {
                            case RANDOM -> {
                                System.out.print("\nВведите количество машин: ");

                                int count = ConsoleIO.checkIntInput(scanner, 0, 10);

                                RandomCarGenerator.fill(filledCars, count, false);
                            }
                            case MANUAL_INPUT -> {
                                System.out.println("\nВведите количество машин:");

                                int count = ConsoleIO.checkIntInput(scanner, 0, 10);

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

                    int sortChoice = ConsoleIO.checkIntInput(scanner, 1, 4);

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