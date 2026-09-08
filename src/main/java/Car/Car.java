package Car;

import java.util.Objects;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
@JsonDeserialize(builder = Car.Builder.class)
public class Car {

    private final double power;
    private final String model;
    private final int year;

    public Car(Builder builder) {
        this.model = builder.model;
        this.year = builder.year;
        this.power = builder.power;
    }
    public double getPower() {
        return power;
    }

    public String getModel() {
        return model;
    }


    public int getYear() {
        return year;
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
        private double power;
        private String model;
        private int year;
        // В СЕТАХ ЛЮБЫЕ ПРОВЕРКИ и null
        public Builder setModel(String model) {
            if (model == null || model.trim().isEmpty()) {
                throw new IllegalArgumentException("Модель не может быть пустой");
            }
            this.model = model;
            return this;
        }

        public Builder setYear(int year) {
            if (year < 2000) {
                throw new IllegalArgumentException("Год должен быть >= 2000");
            }
            this.year = year;
            return this;
        }

        public Builder setPower(double power) {
            if (power <= 0) {
                throw new IllegalArgumentException("Мощность должна быть положительной");
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
