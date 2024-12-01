package main.java.org.simulation.Entities;
import main.java.org.simulation.Constant.Picture;


public class Herbivore extends Creature  {
    private final int HP_DEFAULT=100;
    private static final int SPEED_BY_CELL_DEFAULT=1;
    private  boolean isAlive=true;

    public Herbivore(int hp,int speed) {
        super(Picture.HERBIVORE,
                Picture.HERBIVORE_FIND_EAT_BACKGROUND,speed);
        setHealthPoints(hp);
    }
    public void getDamage(int damage){
        int currentHealthPoints=this.getHealthPoints()-damage;
        if(currentHealthPoints > 0) {
            this.setHealthPoints(currentHealthPoints);
        }
        else{
            this.setHealthPoints(0);
           isAlive=false;
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public boolean makeMove(Entity entity) {
        if (isFood(entity)){
            eat(entity);
            return false;
        }
        if(isPreadtor(entity)){
            Predator predator =(Predator) entity;
            predator.eat(entity);
        }
        return entity instanceof Empty empty;
    }

    @Override
    public void eat(Entity entity) {
        entity=null;
    }

    @Override
    public boolean isFood(Entity entity) {
        return entity instanceof Grass || entity instanceof Tree;
    }
    public boolean isPreadtor(Entity entity) {
        return entity instanceof Predator;
    }
}

