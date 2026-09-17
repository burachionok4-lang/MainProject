import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import Car.*;
import Collection.CustomArrayList;
import IO.*;
import Sorter.*;

class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        CustomArrayList<Car> filledCars = new CustomArrayList<Car>();

        while (true) {

            ConsoleIO.printMainMenu();

            int mainSelectionInt = ConsoleIO.checkedIntInput(scanner, 1, 3);

            MainSelectionOption mainSelectionOption = MainSelectionOption.fromInt(mainSelectionInt);

            switch (mainSelectionOption) {
                case FILL_AND_SORT -> {
                    while (true) {
                        ConsoleIO.printFillOptions();

                        int fillSelectionInt = ConsoleIO.checkedIntInput(scanner, 1, 3);

                        FillSelectionOption fillSelectionOption = FillSelectionOption.fromInt(fillSelectionInt);

                        ConsoleIO.printFillSelection(fillSelectionOption);

                        switch (fillSelectionOption) {
                            case RANDOM -> {
                                System.out.print("\nВведите количество машин: ");

                                int count = ConsoleIO.checkedIntInput(scanner, 1, 100000);

                                RandomCarGenerator.fill(filledCars, count, false);
                            }
                            case MANUAL_INPUT -> {
                                System.out.print("\nВведите количество машин:");

                                int count = ConsoleIO.checkedIntInput(scanner, 1, 10000);

                                CarParser.readFromConsole(count, filledCars, false);
                            }
                            case FROM_FILE -> {
                                while (true) {
                                    String filePath = scanner.nextLine();

                                    try {
                                        CarParser.readJSON(Paths.get(filePath), filledCars, false);
                                    }
                                    catch (IOException e) {
                                        System.out.println(e.getMessage());
                                        System.out.print("Ведите название файла заново: ");

                                        continue;
                                    }

                                    break;
                                }
                            }
                        }

                        if (filledCars.isEmpty()) {
                            System.out.println("Список пуст, попробуйте снова.");

                            continue;
                        }

                        ConsoleIO.printCarList(filledCars, ConsoleIO.printListLimit);

                        break;
                    }

                    while (true) {
                        ConsoleIO.printSortingOptions();

                        int sortChoice = ConsoleIO.checkedIntInput(scanner, 1, 5);

                        SortSelectionOption sortOption = SortSelectionOption.fromInt(sortChoice);

                        switch (sortOption) {
                            case BY_MODEL -> CarSorter.sort(filledCars, new CarSorter.SortByField<>(Car::getModel));
                            case BY_POWER -> CarSorter.sort(filledCars, new CarSorter.SortByField<>(Car::getPower));
                            case BY_YEAR -> CarSorter.sort(filledCars, new CarSorter.SortByField<>(Car::getYear));
                            case BY_ALL -> CarSorter.sort(filledCars, new CarSorter.SortByAllFields());
                        };

                        if(sortOption == SortSelectionOption.EXIT)
                        {
                            break;
                        }

                        System.out.println("\nОтсортированный список:");
                        ConsoleIO.printCarList(filledCars, ConsoleIO.printListLimit);

                    }
                }
                case CHANGE_PRINT_LIMIT -> {
                    ConsoleIO.changePrintLimit();
                    continue;
                }
                case EXIT -> {
                    break;
                }
                default -> System.out.println("Нет такого варианта");

            }

            scanner.close();

            ConsoleIO.printExit();

            return;

        }


    }

}