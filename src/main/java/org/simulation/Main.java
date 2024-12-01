import main.java.org.simulation.Actions.Actions;
import main.java.org.simulation.
import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.main.java.org.simulation.Actions.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final int WIDTH_MAP = 10;
    private static final int HEIGHT_MAP = 10;
    private static final  String greeting="Игра Симуляция\nЧтобы остановить игру,нажмите пробел";

    public static void main(String[] args) throws InterruptedException, IOException {
        MapSimulation mapSimulation =new MapSimulation(WIDTH_MAP, HEIGHT_MAP);
        System.out.println(greeting);
        Simulation simulation= new Simulation(mapSimulation,new Renderer(mapSimulation),new Actions(mapSimulation));
        simulation.startSimulation();

    }
}