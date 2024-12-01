package main.java.org.simulation;
import java.util.Objects;


public class Coordinates {
    private int x;
    private int y;
    private int gScore=0;
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getgScore() {
        return gScore;
    }
    public void setgScore(int gScore) {
        this.gScore = gScore;
    }
    @Override
    public String toString() {
        return "main.java.org.simulation.Coordinates{" + "x=" + x + ", y=" + y + '}';
    }

    // Методы для получения и изменения координат
    public int getX() { return x; }
    public int getY() { return y; }

    public Coordinates add(int x_old, int y_old) {
        return new Coordinates(this.x + x_old, this.y + y_old);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x && y == that.y;
    }

    // Переопределим hashCode для корректной работы с HashMap
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}
