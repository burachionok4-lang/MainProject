package Test;

import Car.Car;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static Car.Car.of;
import Car.CarParser;

public class CarParserTest {
    //Test is combined because it's difficult to separately test each method
    public static boolean writeReadJSON()
    {
        Path path = Paths.get("test.json");

        List<Car> writeCarList = List.of(
                of(100, "A", 1986),
                of(150.1, "Car Says Meow", 2001),
                of(125.5, "Toyota Corolla", 2001),
                of(999.0, "Car Has Long Name", 2052));


        CarParser.writeJSON(path, writeCarList);

        List<Car> readCarList = new ArrayList<Car>();

        CarParser.readJSON(path, readCarList, true);

        return writeCarList.equals(readCarList);
    }
}
