package main.java.org.simulation.Entities;
import main.java.org.simulation.Constant.Picture;



public class Predator  extends Creature {
    private boolean isAlive = true;
    private static final int DAMAGE_DEFAULT = 30;
    private static final int SPEED_BY_CELL_DEFAULT = 2;
    private final int damage;
    public Predator() {
        this(DAMAGE_DEFAULT, SPEED_BY_CELL_DEFAULT);
    }
    public Predator(int damage, int speedByCells) {
        super(Picture.PREDATOR,
                Picture.PREDATOR_FIND_EAT_BACKGROUND,speedByCells);
        this.damage = damage;
    }

    public void damageAttack(Herbivore herbivoreAttacked){
        herbivoreAttacked.getDamage(damage);
    }
    @Override
    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public boolean makeMove(Entity entity) {
        if (isFood(entity)){
            eat(entity);
            return false;
        }
        return entity instanceof Empty empty;
    }

    @Override
    public void eat(Entity entity) {
        if (entity instanceof Herbivore herbivore) {
            damageAttack(herbivore);
        }
    }

    @Override
    public boolean isFood(Entity entity) {
        return entity instanceof Herbivore;
    }
}
