package main.java.org.simulation;
import main.java.org.simulation.Entities.*;

import java.util.ArrayList;
import java.util.List;

public class MapFiller {

    private final InputHandler inputHandler;
    private final EntityManager entityManager;

    public MapFiller(EntityManager entityManager, InputHandler inputHandler) {
        this.entityManager = entityManager;
        this.inputHandler = inputHandler;

    }
    public void fillMapDefault(MapSimulation map) {
        map.addEmptyCells();
        map.addEntity(new Coordinates(4, 5), new Grass());
        map.addEntity(new Coordinates(7, 5), new Grass());
        map.addEntity(new Coordinates(3, 8), new Tree());
        map.addEntity(new Coordinates(7, 10), new Tree());
        map.addEntity(new Coordinates(8, 8), new Tree());
        map.addEntity(new Coordinates(5, 5), new Herbivore(30, 1));
        map.addEntity(new Coordinates(1, 5), new Herbivore(30, 2));
        map.addEntity(new Coordinates(6, 6), new Rock());
        map.addEntity(new Coordinates(5, 6), new Rock());
        map.addEntity(new Coordinates(7, 9), new Rock());
        map.addEntity(new Coordinates(1, 1), new Predator(10, 2));
    }
    public void fillMapByUser(MapSimulation map,Renderer renderer) throws InterruptedException {
        boolean isEditing = true;
        while (isEditing) {
            renderer.render(map);
            System.out.println("1. Добавить сущность\n2. Удалить сущность\n3. Закончить редактирование");
            int action = inputHandler.getAnswerFromUser("Выберите действие:", 1).get(0);
            switch (action) {
                case 1: // Добавить сущность
                    List<Integer> coordinates = inputHandler.getAnswerFromUser("Введите координаты X,Y:", 2);
                    int x = coordinates.get(0);
                    int y = coordinates.get(1);

                    System.out.println("1. Хищник\n2. Травоядное\n3. Трава\n4. Скала\n5. Дерево");
                    int entityType = inputHandler.getAnswerFromUser("Выберите тип сущности:", 1).get(0);

                    List<Integer> properties = new ArrayList<>();
                    if (entityType == 1 || entityType == 2) { // Predator или Herbivore
                        String message = (entityType == 1)
                                ? "Введите силу атаки и скорость (через запятую):"
                                : "Введите здоровье и скорость (через запятую):";
                        properties = inputHandler.getAnswerFromUser(message, 2);
                    }

                    Entity entity = entityManager.createEntity(entityType, properties);
                    map.addEntity(new Coordinates(x, y), entity);
                    break;

                case 2: // Удалить сущность
                    List<Integer> coordinatesToRemove = inputHandler.getAnswerFromUser("Введите координаты X,Y для удаления:", 2);
                    map.removeEntity(new Coordinates(coordinatesToRemove.get(0), coordinatesToRemove.get(1)));
                    break;

                case 3: // Закончить редактирование
                    isEditing = false;
                    break;

                default:
                    System.out.println("Неверное действие, попробуйте снова.");
            }
        }
    }
}
