package Entities;
import
public abstract class Entity{

    private Coordinates currentCoordinates;
    private String picture;

    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setCurrentCoordinates(Coordinates currentCoordinates) {
            this.currentCoordinates = currentCoordinates;
    }

    public Coordinates getCurrentCoordinates() {
        return currentCoordinates;
    }

    public void clearCoordinates() {
        this.currentCoordinates = null;
    }
}