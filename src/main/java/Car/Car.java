package Car;

import java.util.Objects;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = Car.Builder.class)
public class Car {
    private final String model;
    private final double power;
    private final int year;

    public Car(Builder builder) {
        this.model = builder.model;
        this.power = builder.power;
        this.year = builder.year;
    }
    public String getModel() {
        return model;
    }
    public double getPower() {
        return power;
    }
    public int getYear() {
        return year;
    }

    public static Car of(String model, double power, int year) {
        return new Car.Builder()
                .setModel(model)
                .setPower(power)
                .setYear(year)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car that = (Car) o;
        return Objects.equals(model,that.model)
                && power == that.power
                && year == that.year;

    }

    @Override
    public int hashCode() {
        return Objects.hash(model, power, year);
    }

    @Override
    public String toString() {
        return String.format("Car{model='%s', power=%s, year=%d}",
                model, power, year);
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {
        private static final int MIN_YEAR = 1886;
        private static final int MAX_YEAR = java.time.Year.now().getValue() + 1;
        private static final double MIN_POWER = 1.0;
        private static final double MAX_POWER = 2000.0;
        private static final int MAX_MODEL_LENGTH = 100;

        private double power;
        private String model;
        private int year;

        public Builder setModel(String model) {
            if (model == null || model.isBlank()) {
                throw new IllegalArgumentException("Модель не может быть пустой");
            }
            String trimmed = model.trim();
            if (trimmed.length() > MAX_MODEL_LENGTH) {
                throw new IllegalArgumentException(
                        "Модель не может быть длиннее " + MAX_MODEL_LENGTH + " символов");
            }
            this.model = trimmed;
            return this;

        }

        public Builder setYear(int year) {
            if (year < MIN_YEAR || year > MAX_YEAR) {
                throw new IllegalArgumentException(
                        "Год должен быть в диапазоне [" + MIN_YEAR + ", " + MAX_YEAR + "], получено: " + year);
            }
            this.year = year;
            return this;
        }

        public Builder setPower(double power) {
            if (Double.isNaN(power) || Double.isInfinite(power)) {
                throw new IllegalArgumentException("Мощность должна быть конечным числом");
            }
            if (power < MIN_POWER || power > MAX_POWER) {
                throw new IllegalArgumentException(
                        "Мощность должна быть в диапазоне [" + MIN_POWER + ", " + MAX_POWER + "], получено: " + power);
            }
            this.power = power;
            return this;
        }

        public Car build() {
            if (model == null) {
                throw new IllegalStateException("Модель не задана");
            }
            if (year == 0) {
                throw new IllegalStateException("Год не задан");
            }
            if (power == 0) {
                throw new IllegalStateException("Мощность не задана");
            }
            return new Car(this);
        }
    }
}
