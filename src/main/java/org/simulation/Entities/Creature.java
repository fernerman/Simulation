package main.java.org.simulation.Entities;

public abstract class Creature extends Entity  {

    private int numberPassedCoordinates;
    private int healthPoints;
    private boolean isAlive;
    public Creature(String picture, String backgroundPicture,int speed) {
        super(picture, backgroundPicture);
        this.numberPassedCoordinates=speed;
    }
    public boolean isAlive() {
        return isAlive;
    }
    public abstract boolean isFood(Entity entity);
    public abstract boolean makeMove(Entity entity);
    public abstract void eat(Entity entity);
    public int getCountPassedCoordinatesInTurn() {
        return numberPassedCoordinates;
    }
    public void setCountPassedCoordinatesInTurn(int numberPassedCoordinates) {
        this.numberPassedCoordinates = numberPassedCoordinates;
    }
    public int getHealthPoints(){
        return healthPoints;
    }
    public void setHealthPoints(int healthPoints){
        this.healthPoints = healthPoints;
    }

}
