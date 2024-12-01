import java.util.*;
public class Coordinates {
    private int x;
    private int y;
    //расстояние от начального узла до текущего
    private int gScore=0;
    //сумма g and h
    private int hScore=0;

    private Object  parent=null;
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getgScore() {
        return gScore;
    }

    public int gethScore() {
        return hScore;
    }

    public void setgScore(int gScore) {
        this.gScore = gScore;
    }

    public void sethScore(int hScore) {
        this.hScore = hScore;
    }

    @Override
    public String toString() {
        return "Coordinates{" + "x=" + x + ", y=" + y + '}';
    }

    // Методы для получения и изменения координат
    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

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

    // Полная оценка пути f = g + h
    public int getFCost() {
        return gScore + hScore;
    }

}
