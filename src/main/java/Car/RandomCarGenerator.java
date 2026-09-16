package Car;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public final class RandomCarGenerator {
    private static final String[] MODELS = {
            "Toyota Camry", "BMW X5", "Audi A6", "Mercedes-Benz E-Class",
            "Volkswagen Passat", "Ford Mustang", "Chevrolet Corvette",
            "Hyundai Sonata", "Nissan Leaf", "Tesla Model S",
            "Kia Rio", "Skoda Octavia", "Mazda CX-5", "Subaru Outback",
            "Volvo XC90", "Lexus RX", "Porsche 911", "Jaguar F-Type"
    };
    private static final Random RANDOM = new Random();
    private RandomCarGenerator() {}
    public static Car generate(){
        return new Car.Builder()
                .setModel(MODELS[RANDOM.nextInt(MODELS.length)])
                .setYear(Car.MIN_YEAR+RANDOM.nextInt(Car.MAX_YEAR-Car.MIN_YEAR+1))
                .setPower(Car.MIN_POWER+RANDOM.nextDouble()*(Car.MAX_POWER-Car.MIN_POWER))
                .build();
    }
    public static void fill(List<Car> dest, int count, boolean clearDest) {
        if (count < 0) throw new IllegalArgumentException("count < 0");
        if (clearDest) dest.clear();
        //Попытка в стрим
        Stream.generate(RandomCarGenerator::generate)
                .limit(count)
                .forEach(dest::add);
    }
}

