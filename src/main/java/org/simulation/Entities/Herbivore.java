package Entities;

import Constant.Picture;

public class Herbivore extends Creature {

    public Herbivore() {
        setPicture(Picture.HERBIVORE);
        setNumberPassedCoordinates(1);
        setHealthPoints(30);
    }

    public boolean isAlive(int currentHealthPoints){
        return currentHealthPoints>0;
    }
    public void getDamage(int damage){
        int currentHealthPoints=this.getHealthPoints()-damage;
        if(isAlive(currentHealthPoints)) {
            this.setHealthPoints(currentHealthPoints);
        }
        else{
            clearCoordinates();
        }
    }

    @Override
    public boolean makeMove(Entity entity) {
        boolean isMoving = false;
        switch (entity) {
            case Grass grass -> {
                eat(grass);
            }
            case Predator preadator -> {
                preadator.eat(this);
            }
            case Empty empty -> {
                isMoving = true;
            }
            default -> {
            }
        }

        return isMoving;
    }

    @Override
    public void eat(Entity entity) {

    }


}

