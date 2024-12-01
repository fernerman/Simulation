import main.java.org.simulation.Entities.Entity;

import java.util.HashMap;

public class Renderer {
    private MapSimulation mapSimulation;
    private HashMap<Coordinates, String> previousState;

    public Renderer(MapSimulation mapSimulation) {
        this.mapSimulation = mapSimulation;
        this.previousState = new HashMap<>();
    }

    public void clearConsole() {
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }
    public void render() {

        for(int x = 0; x< mapSimulation.getWidthMap(); x++){
            for(int y = 0; y< mapSimulation.getHeightMap(); y++){
                Coordinates coord = new Coordinates(x, y);
                Entity entity = mapSimulation.getEntity(coord);
                System.out.print(entity.getPicture());
            }
            System.out.println();
        }

    }

}





