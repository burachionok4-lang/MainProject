package Car;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static Car.Car.of;

public class CarParserTest {
    //Test is combined because it's difficult to separately test each method
    @Test
    public void writeReadJSONTest()
    {
        Path path = Paths.get("test.json");

        List<Car> writeCarList = List.of(
                of("Aaaa", 100, 1986),
                of("Car Says Meow",150.1,2001),
                of("Car That is 3rd",333.3,  2003),
                of("Car That is 4th",444.4,  2004),
                of("Toyota Corolla",125.5,  2002),
                of("Ford Focus",125.5,  1981),
                of("Ford Escort",133.0,  1980),
                of("Ford Escort Mk2", 145.0, 1980),
                of("Lada Largus",122.0,  1999),
                of("Volvo XC90",149.0,  2015),
                of("Lada Kalina",98.0,  2004),
                of("Honda Accord", 110.0, 2001),
                of("Lada Vesta",99.0,  2015),
                of("Car Has Very Very Long Name",999.0,  2025));


        CarParser.writeJSON(path, writeCarList);

        List<Car> readCarList = new ArrayList<Car>();

        CarParser.readJSON(path, readCarList, true);

        assertEquals(writeCarList, readCarList);
    }
}
