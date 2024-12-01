import Entities.*;

import java.util.List;

public class Actions {
    private final MapSimulation mapSimulation;
    public Actions(MapSimulation mapSimulation) {
        this.mapSimulation = mapSimulation;
    }

    public void initActions(){
        //создание creatures
        mapSimulation.addEmptyCells();

        mapSimulation.addEntity(new Coordinates(5,1),new Herbivore());
        mapSimulation.addEntity(new Coordinates(1,1),new Predator());
        mapSimulation.addEntity(new Coordinates(3,0),new Rock());
        mapSimulation.addEntity(new Coordinates(3,1),new Rock());
        mapSimulation.addEntity(new Coordinates(3,2),new Rock());
        mapSimulation.addEntity(new Coordinates(3,3),new Rock());
        mapSimulation.addEntity(new Coordinates(3,4),new Rock());
        mapSimulation.addEntity(new Coordinates(8,9),new Rock());
        mapSimulation.addEntity(new Coordinates(7,9),new Rock());

    }
    public void turnActions (){
        for(int x = 0; x< mapSimulation.getWidthMap(); x++) {
            for (int y = 0; y < mapSimulation.getHeightMap(); y++) {
                Entity entity = mapSimulation.getEntity(new Coordinates(x, y));
                if (entity instanceof Predator creature) {
                    for(int move=1;move<=creature.getNumberPassedCoordinates();move++){
                        //map.moveCreature(creature,Constant.DirectionOnMap.LEFT,Constant.DirectionOnMap.UP);
                        PathFinder pathFinder=new PathFinder(mapSimulation,creature.getCurrentCoordinates());
                        List<Coordinates> coors=pathFinder.findPathToTarget(creature.getCurrentCoordinates());
                        for(var cell: coors){
                            System.out.println(cell);
                        }
                        for(Coordinates c:coors){
                            mapSimulation.addEntity(c,new Grass());
                        }
                    }
                }
            }
        }
    }
}
