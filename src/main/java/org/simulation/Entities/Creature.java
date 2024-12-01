package Entities;

import java.util.Random;

public abstract class Creature extends Entity {

    public Random rand = new Random();
    private int numberPassedCoordinates;
    private int healthPoints;
    public abstract boolean makeMove(Entity entity);
    public abstract void eat(Entity entity);
    public int getNumberPassedCoordinates() {
        return numberPassedCoordinates;
    }
    public void setNumberPassedCoordinates(int numberPassedCoordinates) {
        this.numberPassedCoordinates = numberPassedCoordinates;
    }
    public int getHealthPoints(){
        return healthPoints;
    }
    public void setHealthPoints(int healthPoints){
        this.healthPoints = healthPoints;
    }

}
