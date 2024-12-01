package Entities;

import Constant.Picture;

public class Predator extends Creature {


    public Predator() {
         setPicture(Picture.PREDATOR);
         setNumberPassedCoordinates(2);
    }

    public void damageAttack(Herbivore herbivoreAttacked){
        int damagePoints = 10;
        herbivoreAttacked.getDamage(damagePoints);
    }

    @Override
    public boolean makeMove(Entity entity) {
        boolean isMoving = false;
        switch (entity) {
            case Herbivore herbivore -> {
                eat(herbivore);
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
        Herbivore herbivore = (Herbivore) entity;
        damageAttack(herbivore);
    }


}
