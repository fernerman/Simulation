package main.java.org.simulation.Entities;
import main.java.org.simulation.Constant.Picture;
import main.java.org.simulation.Coordinates;

import static main.java.org.simulation.Constant.Picture.EMPTY_BACKGROUND;

public abstract class Entity{
    private String picture;
    private String backgroundPicture;
    private boolean isAlive;

    public Entity(String picture, String backgroundPicture) {
        this.picture = picture;
        this.backgroundPicture = backgroundPicture;
        isAlive = true;
    }
    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public String getBackgroundPicture() {
        return backgroundPicture;
    }
    public void setBackgroundPicture(String backgroundPicture) {
        this.backgroundPicture = backgroundPicture;
    }
    public String getPicture() {
        return picture;
    }


}