package main.java.org.simulation;
import main.java.org.simulation.Entities.Creature;
import main.java.org.simulation.Entities.Entity;

import java.util.*;

import static java.lang.Math.abs;

public class PathFinder {

    private final MapSimulation map;
    private final Creature creature;
    public PathFinder( Creature creature,MapSimulation map, Coordinates start) {
        this.map = map;
        this.creature = creature;
    }
    public boolean isTarget(Coordinates coordinates){
        Entity entity =map.getEntity(coordinates);
        return  creature.isFood(entity);
    }
    public List<Coordinates> findPathToTarget(Coordinates start) {

        Map<Coordinates, Optional<Coordinates> > cameFrom = new HashMap<Coordinates, Optional<Coordinates>>();
        Queue<Coordinates> openSet = new LinkedList<>();
        openSet.add(start);
        while (!openSet.isEmpty()) {
            Coordinates current = openSet.poll();
            // Проверка, является ли текущая ячейка целью
            if (isTarget(current)) {
                return reconstructPath(current,start,cameFrom);
            }
            // Получаем соседей и расширяем границы поиска
            for (Coordinates neighbor : map.getNeighborsCurrentCell(current)) {
                if(cameFrom.containsKey(neighbor)) continue;
                int tentativeG = current.getgScore() + 1; // gScore текущего + стоимость перехода к соседу
                if (!openSet.contains(neighbor) || tentativeG<neighbor.getgScore()) {
                    cameFrom.put(neighbor, Optional.ofNullable(current));
                    neighbor.setgScore(tentativeG);
                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }
        // Если цель не найдена
        return Collections.emptyList();
    }
    private List<Coordinates> reconstructPath(Coordinates current, Coordinates start,Map<Coordinates, Optional<Coordinates> > cameFrom) {
        List<Coordinates> path = new ArrayList<>();
        // Формирование пути от end к start, если путь существует
        while (!current.equals(start)) {
            path.add(current);
            current = cameFrom.get(current).orElseThrow();
        }
        path.add(start);
        Collections.reverse(path);
        if (!path.isEmpty()) {
            path.removeLast();
        }
        return path;
    }
}

