
import main.java.org.simulation.Entities.Creature;
import main.java.org.simulation.Entities.Empty;
import main.java.org.simulation.Entities.Entity;
import main.java.org.simulation.Entities.Rock;

import java.util.*;

public class MapSimulation {

    private final HashMap<Coordinates, Entity> map;
    private final int widthMap;
    private final int heightMap;

    public MapSimulation(int widthMap, int heightMap) {
        this.widthMap=widthMap;
        this.heightMap=heightMap;
        this.map = new HashMap<Coordinates,Entity>();
    }

    public void addEmptyCells(){
        for(int x=0;x<widthMap;x++){
            for(int y=0;y<heightMap;y++){
                map.put(new Coordinates(x,y), new Empty());
            }
        }
    }
    public void addEntity(Coordinates coordinates, Entity entity) {
        entity.setCurrentCoordinates(coordinates);
        map.put(coordinates, entity);
    }

    public  Entity getEntity(Coordinates coordinates) {
        Entity entity=map.get(coordinates);
        if(entity.getCurrentCoordinates()==null){
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
        return new_coordinates.getX() < 0 || new_coordinates.getY() < 0 || new_coordinates.getX() >= widthMap || new_coordinates.getY() >= heightMap;
    }

    public void moveCreature(Creature creature, int coord_x, int coord_y) {

        Coordinates old_coords=creature.getCurrentCoordinates();
        Coordinates new_coordinates =old_coords.add(coord_x,coord_y);
        if(!isEndMap(new_coordinates)){
            Entity entityAtNewCoordinates = getEntity(new_coordinates);

            if(creature.makeMove(entityAtNewCoordinates)){
                creature.setCurrentCoordinates(new_coordinates);
                removeEntity(old_coords);
                map.put(new_coordinates, creature);
            }

        }

    }


    public void removeEntity(Coordinates coordinates) {
        Entity entity = map.get(coordinates);
//        if (entity != null) {
//            entity.clearCoordinates();
//        }
        map.put(coordinates,new Empty());
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
