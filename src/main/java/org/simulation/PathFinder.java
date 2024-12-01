import main.java.org.simulation.Constant.DirectionOnMap;
import main.java.org.simulation.Entities.Entity;
import main.java.org.simulation.Entities.Herbivore;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

public class PathFinder {

    private final MapSimulation map;

    public PathFinder( MapSimulation map, Coordinates start) {
        this.map = map;

    }

    public boolean isTarget(Coordinates coordinates){
        Entity entity =map.getEntity(coordinates);
        if (entity instanceof Herbivore) {
            return true;
        }
        return  false;
    }

    public List<Coordinates> findPathToTarget(Coordinates start) {

        Map<Coordinates, Optional<Coordinates> > cameFrom = new HashMap<Coordinates, Optional<Coordinates>>();
        //Queue<Coordinates> openSet =  new PriorityQueue<>(Comparator.comparingInt(Coordinates::getFCost));

        Queue<Coordinates> openSet = new LinkedList<>();
        openSet.add(start);

        while (!openSet.isEmpty()) {
            Coordinates current = openSet.poll();

            // Проверка, является ли текущая ячейка целью
            if (isTarget(current)) { // isTarget() — метод проверки, является ли клетка целью
                return reconstructPath(current,start,cameFrom);
            }

            // Получаем соседей и расширяем границы поиска
            for (Coordinates neighbor : getNeighborsCurrentCell(current)) {
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
        return path;
    }
    public int getHeuristicValue(Coordinates goal, Coordinates next) {
        return abs(goal.getX()-next.getX()) + abs(goal.getY()-next.getY());
    }
    public int getCostSoFar(Coordinates current,Coordinates next) {

        return 1;
    }
    public Set<Coordinates> getNeighborsCurrentCell( Coordinates current) {
        HashSet<Coordinates> neighbors = new HashSet<>();
        neighbors.add(current.add(0, DirectionOnMap.UP));
        neighbors.add(current.add(0,DirectionOnMap.DOWN));
        neighbors.add (current.add(DirectionOnMap.LEFT,0));
        neighbors.add(current.add(DirectionOnMap.RIGHT,0));



        var d =neighbors.stream().filter(e->!map.isEndMap(e) && !map.isEmptyCell(e)).collect(Collectors.toSet() );
        return d;
    }
}

