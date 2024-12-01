import main.java.org.simulation.Actions.Actions;

import java.util.Scanner;

public class Simulation {
    private MapSimulation mapSimulation;
    private int currentTurn=0;
    private Renderer renderer;
    private Actions actions;
    private boolean pause=false;

    private final  String msgPause="Вы поставили паузу\nВведите пробел, чтобы снять паузу.";

    public void nextTurn() throws InterruptedException {
        if(currentTurn>0){
            actions.turnActions();
        }
        else {
            actions.initActions();

        }
        renderer.render();
        Thread.sleep(2000);
        renderer.clearConsole();
        currentTurn++;
    }
    public void startSimulation() throws InterruptedException {
        while(!pause){
            nextTurn();
        }

    }
    public void pauseSimulation(){
        Scanner scanner = new Scanner(System.in);
        String letterToStopWait =scanner.nextLine();
        if(letterToStopWait.equals(" ")){
            this.pause=!pause;
        }
    }

    public Simulation(MapSimulation mapSimulation, Renderer renderer, Actions actions) {
        this.mapSimulation = mapSimulation;
        this.renderer = renderer;
        this.actions = actions;
    }
}
