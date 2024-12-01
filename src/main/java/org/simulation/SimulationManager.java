package main.java.org.simulation;
public class SimulationManager {
    private final Simulation simulation;
    private Thread simulationThread;

    public SimulationManager(Simulation simulation) {
        this.simulation = simulation;
    }

    public void startSimulation() throws InterruptedException {
        simulationThread = new Thread(() -> {
            try {
                while (simulation.isRunning()) {
                    if (!simulation.isPaused()) {
                        simulation.nextTurn();
                    } else {
                        Thread.sleep(200);
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        simulationThread.start();
        simulationThread.join();
    }
}
