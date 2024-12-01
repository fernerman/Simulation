package main.java.org.simulation;

import main.java.org.simulation.Actions.Actions;

public class Simulation {
    private Actions actions;
    public  volatile boolean pause=false;
    private volatile boolean isRunning = true;

    public Simulation( Actions actions) {
        this.actions=actions;
    }
    public void nextTurn() throws InterruptedException {
        actions.turnActions();
    }
    public void startSimulation() throws InterruptedException {
        actions.initActions();
    }
    public synchronized void togglePause() {
        pause = !pause;
        System.out.println(pause ? "Симуляция приостановлена." : "Симуляция возобновлена.");
        System.out.println("Для выхода из Симуляции введите y.");
    }
    public boolean isRunning() {
        return isRunning;
    }
    public synchronized boolean isPaused() {
        return pause;
    }
    public synchronized void stopSimulation() {
        isRunning=false;
    }
}


