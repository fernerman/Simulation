package main.java.org.simulation;

import main.java.org.simulation.Constant.DirectionOnMap;
import main.java.org.simulation.Entities.Creature;
import main.java.org.simulation.Entities.Empty;
import main.java.org.simulation.Entities.Entity;
import main.java.org.simulation.Entities.Rock;

import java.util.*;
import java.util.stream.Collectors;

public class MapSimulation {

    private  Map<Coordinates, Entity> map;
    private  int widthMap=10;
    private  int heightMap=10;

    public MapSimulation() {
        this.map = new HashMap<Coordinates,Entity>();
        addEmptyCells();
    }
    public MapSimulation(int widthMap, int heightMap) {
        this.widthMap=widthMap;
        this.heightMap=heightMap;
        this.map = new HashMap<Coordinates,Entity>();
    }

    public void addEmptyCells(){
        for(int x=1;x<=widthMap;x++){
            for(int y=1;y<=heightMap;y++){
                map.put(new Coordinates(x,y), new Empty());
            }
        }
    }

    public Map<Coordinates, Entity> getMapWithCreatures() {
        return map.entrySet().stream()
                .filter(entry -> entry.getValue() instanceof Creature)  // Отфильтровываем только те элементы, где значение - Creature
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public List<Empty> getEmptyCreatures() {
        return map.values().stream()  // Получаем поток значений из Map
                .filter(value -> value instanceof Empty)  // Фильтруем, оставляем только объекты типа Empty
                .map(value -> (Empty) value)  // Преобразуем элементы в тип Empty
                .collect(Collectors.toList());  // Собираем отфильтрованные элементы в список
    }

    public Optional<Coordinates> isFoundFoodNearby(Creature creature,Set<Coordinates> coordinates) {
        Optional<Coordinates> coordinatesFood = Optional.empty();
        for(Coordinates coordinate: coordinates){
            boolean isFood =creature.isFood(getEntity(coordinate));
            if(isFood){
                return Optional.of(coordinate);
            }
        }
        return coordinatesFood;
    }

    public Set<Coordinates> getNeighborsCurrentCell( Coordinates current) {
        HashSet<Coordinates> neighbors = new HashSet<>();
        neighbors.add(current.add(0, DirectionOnMap.UP));
        neighbors.add(current.add(0,DirectionOnMap.DOWN));
        neighbors.add (current.add(DirectionOnMap.LEFT,0));
        neighbors.add(current.add(DirectionOnMap.RIGHT,0));
        Set<Coordinates> coordinatesNearby =neighbors.stream().filter(e->!isEndMap(e) && !isEmptyCell(e)).collect(Collectors.toSet() );
        return coordinatesNearby;
    }

    public void addEntity(Coordinates coordinates, Entity entity) {
        map.put(coordinates, entity);
    }

    public  Entity getEntity(Coordinates coordinates) {
        Entity entity=map.get(coordinates);
        if(entity==null){
            map.put(coordinates,new Empty());
        }
        return map.get(coordinates);
    }

    public  boolean isEmptyCell(Coordinates new_coordinates) {
        boolean check=false;
        Entity entityAtNewCoordinates = getEntity(new_coordinates);
        if(entityAtNewCoordinates instanceof Rock){
            check=true;
        }
        return check;
    }

    public  boolean isEndMap(Coordinates new_coordinates) {
        return new_coordinates.getX() < 1 || new_coordinates.getY() < 1 || new_coordinates.getX() > widthMap || new_coordinates.getY() > heightMap;
    }

    public void moveCreature(Creature creature, Coordinates oldCoordinates,Coordinates newCoordinates) {
        Entity entity =map.get(newCoordinates);
        if(creature.makeMove(entity)){
                map.put(newCoordinates, creature);
                removeEntity(oldCoordinates);
            }
    }

    public  void removeEntity(Coordinates coordinates) {
        map.put(coordinates,new Empty());
    }

    public void setWidthMap(int widthMap) {
        this.widthMap = widthMap;
    }

    public void setHeightMap(int heightMap) {
        this.heightMap = heightMap;
    }

    public int getHeightMap() {
        return heightMap;
    }
    public int getWidthMap() {
        return widthMap;
    }
    public Set<Coordinates> getTotalCoordinates(){
        return map.keySet();
    }
}
