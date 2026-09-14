package Sorter;

import Car.Car;

import java.util.Comparator;
import java.util.List;

public class SortByPower implements SortCarStrategy {
    private static final Comparator<Car> COMPARATOR =
            Comparator.comparing(Car::getPower);
    @Override
    public void sortCars(List<Car> cars) {
        cars.sort(COMPARATOR);
    }
}
