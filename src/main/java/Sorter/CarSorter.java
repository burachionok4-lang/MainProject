package Sorter;
import Car.*;

import java.util.List;

public class CarSorter {
    SortCarStrategy strategy;
    public CarSorter(SortCarStrategy strategy){
        this.strategy = strategy;
    }
    public void sortCars(List<Car> cars) {
        strategy.sortCars(cars);
    }
}
