package main.java.org.simulation.Entities;

import java.util.List;

public class EntityManager {
    public Entity createEntity(int entityType, List<Integer> properties) {
        switch (entityType) {
            case 1:
                int damage= properties.get(0);
                int speedPredator= properties.get(1);
                return new Predator(damage, speedPredator);
            case 2:
                int hp= properties.get(0);
                int speedHerbivore= properties.get(1);
                return new Herbivore(hp, speedHerbivore);
            case 3:
                return new Grass();
            case 4:
                return new Rock();
            case 5:
                return new Tree();
            default:
                throw new IllegalArgumentException("Неверный тип сущности!");
        }
    }
}
