package Car;

import java.util.Objects;

public class Car {

    private double power;
    private String model;
    private int year;

    public Car(Double power, String model, Integer year) {
        this.power = power;
        this.model = model;
        this.year = year;
    }
    public Car() {
    }
    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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
}
