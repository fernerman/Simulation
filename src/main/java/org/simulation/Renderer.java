package main.java.org.simulation;

import main.java.org.simulation.Constant.Picture;
import main.java.org.simulation.Entities.Entity;

public class Renderer {
    public Renderer() {
    }

    public void render(MapSimulation mapSimulation) throws InterruptedException {
        StringBuilder output = new StringBuilder();

        // Печать заголовка с номерами столбцов
        for (int i = 1; i <= mapSimulation.getHeightMap(); i++) {
            output.append("\t").append(i);
        }
        output.append("\n");
        for(int y = 1; y<= mapSimulation.getHeightMap(); y++)
        {
            output.append(String.format("%2d\t", y));
            for(int x = 1; x<= mapSimulation.getWidthMap(); x++){
                Coordinates coord = new Coordinates(x, y);
                Entity entity = mapSimulation.getEntity(coord);
                output.append(entity.getBackgroundPicture()).append(entity.getPicture()).append("\t");
            }
            output.append(Picture.RESET).append("\n");
        }
        System.out.print(output.toString());
        Thread.sleep(2000);

        for(var creature:mapSimulation.getEmptyCreatures()){
            creature.setBackgroundPicture(Picture.EMPTY_BACKGROUND);
        }
        System.out.println();
    }

}





