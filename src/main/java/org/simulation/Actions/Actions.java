package main.java.org.simulation.Actions;
import main.java.org.simulation.*;
import main.java.org.simulation.InputHandler;
import main.java.org.simulation.Entities.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class Actions {

    MapSimulation mapSimulation;
    private Renderer renderer;
    private InputHandler inputHandler;
    private MapFiller mapFiller;
    public Actions(MapFiller mapFiller,Renderer renderer, MapSimulation mapSimulation, InputHandler inputHandler) {
        this.mapFiller = mapFiller;
        this.renderer=renderer;
        this.mapSimulation=mapSimulation;
        this.inputHandler = inputHandler;
    }
    public void initActions() throws InterruptedException {
        System.out.println(InputHandler.MESSAGE_GREETING);
        boolean flag=true;
        while (flag) {
            int wayToFillMap= inputHandler.getWayFillingMapSimulation(InputHandler.MESSAGE_MAP_FILLING,1);
            if(wayToFillMap==1){
                mapFiller.fillMapByUser(mapSimulation,renderer);
                flag=false;
            }
            if(wayToFillMap==2){
                mapFiller.fillMapDefault(mapSimulation);
                flag=false;
            }
        }
        renderer.render(mapSimulation);
    }
    public void setBackgroundColorToTarget(Creature creature, List<Coordinates> coordinates) {
        String color =creature.getBackgroundPicture();
        for(var cell: coordinates){
            Entity entity =mapSimulation.getEntity(cell);
            entity.setBackgroundPicture(color);
            mapSimulation.addEntity(cell,entity);
        }
    }
    public void turnActions () throws InterruptedException {
        renderer.render(mapSimulation);
        Map<Coordinates, Entity> mapWithCreatures =mapSimulation.getMapWithCreatures();
        for(var entrySet:mapWithCreatures.entrySet()){
            Coordinates coordinate =entrySet.getKey();
            Creature creature=(Creature)entrySet.getValue();
            if(!creature.isAlive()) {
                mapSimulation.removeEntity(coordinate);
                continue;
            }
            PathFinder pathFinder=new PathFinder(creature,mapSimulation,coordinate);
            List<Coordinates> coors=pathFinder.findPathToTarget(coordinate);
            setBackgroundColorToTarget(creature,coors);
            if(!coors.isEmpty()){
                Coordinates coordinatesToMove =coors.get(Math.min(creature.getCountPassedCoordinatesInTurn(),coors.size()-1));
                mapSimulation.moveCreature(creature,coordinate,coordinatesToMove);
                Set<Coordinates> coordinatesNearbyCreature=mapSimulation.getNeighborsCurrentCell(coordinatesToMove);
                Optional<Coordinates> coordinatesToFood =mapSimulation.isFoundFoodNearby(creature,coordinatesNearbyCreature);
                coordinatesToFood.ifPresent(coordinates -> creature.makeMove(mapSimulation.getEntity(coordinates)));
            }
        }
    }
}
