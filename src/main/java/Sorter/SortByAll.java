package Sorter;

import Car.Car;

import java.util.Comparator;
import java.util.List;

public class SortByAll implements SortCarStrategy {
    private static final Comparator<Car> COMPARATOR =
            Comparator.comparing(Car::getModel)
                    .thenComparingDouble(Car::getPower)
                    .thenComparingInt(Car::getYear);
    @Override
    public void sortCars(List<Car> cars) {
        cars.sort(COMPARATOR);
    }
}