package main.java.org.simulation.Entities;
import main.java.org.simulation.Constant.Picture;

import static main.java.org.simulation.Constant.Picture.EMPTY_BACKGROUND;

public class Empty extends Entity {

    public Empty() {
        super(Picture.EMPTY,Picture.EMPTY_BACKGROUND);
    }
}
