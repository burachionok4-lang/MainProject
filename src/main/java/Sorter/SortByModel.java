package Sorter;
import Car.Car;
import java.util.Comparator;
import java.util.List;

public class SortByModel implements SortCarStrategy {
    private static final Comparator<Car> COMPARATOR =
            Comparator.comparing(Car::getModel);
    @Override
    public void sortCars(List<Car> cars) {
        cars.sort(COMPARATOR);
    }
}
