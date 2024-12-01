package main.java.org.simulation;
import java.io.IOException;
import java.util.Scanner;
import main.java.org.simulation.Actions.Actions;
import main.java.org.simulation.Entities.EntityManager;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        Scanner scanner = new Scanner(System.in);
        Renderer renderer = new Renderer();
        MapSimulation mapSimulation=new MapSimulation();
        EntityManager entityManager = new EntityManager();

        InputHandler inputHandlerForSimilation = new InputHandler(scanner,renderer);
        MapFiller mapFiller = new MapFiller(entityManager, inputHandlerForSimilation);

        Actions actions=new Actions(mapFiller,renderer,mapSimulation, inputHandlerForSimilation);
        Simulation simulation= new Simulation(actions);
        SimulationManager simulationManager = new SimulationManager(simulation);
        try{
            inputHandlerForSimilation.setSimulation(simulation);
            simulation.startSimulation();
            inputHandlerForSimilation.startListening();
            simulationManager.startSimulation();
        }
        catch(InterruptedException e){
            System.out.println("Ошибка выполнения симуляции: " + e.getMessage());
        }
        finally{
            System.out.println("Программа завершена.");
        }

    }
}
