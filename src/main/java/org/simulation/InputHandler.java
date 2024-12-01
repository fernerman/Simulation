package main.java.org.simulation;

import main.java.org.simulation.Entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputHandler {

    private Renderer renderer;
    private Scanner scanner;
    private Simulation simulation;
    public final static String MESSAGE_GREETING = "Добро пожаловать в \"Симуляцию\"!";
    public final static String MESSAGE_MAP_FILLING = "Каким образом хотите заполнить карту?\n1. Самостоятельно\n2. По умолчанию.";
    public final static String MESSAGE_WRONG_INPUT = "Введите корректно значение!";
    public final static int RECEIVED_ONE_VALUE = 1;
    public final static int RECEIVED_TWO_VALUES = 2;

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    public InputHandler(Scanner scanner, Renderer renderer) {
        this.scanner=scanner;
        this.renderer=renderer;
    }

    public void startListening() throws InterruptedException {
        Thread thread =new Thread(() -> {
            while (simulation.isRunning()) {
                String input = scanner.nextLine();
                if (" ".equals(input)) {
                    simulation.togglePause();
                }
                if ("y".equals(input)) {
                    simulation.stopSimulation();
                }
            }
        });
        thread.start();
    }
    public int getWayFillingMapSimulation(String message, int countInputValues){
        int wayToFillMap=getAnswerFromUser(InputHandler.MESSAGE_MAP_FILLING,1).getFirst();
        if(wayToFillMap!=RECEIVED_ONE_VALUE && wayToFillMap!=RECEIVED_TWO_VALUES){
            System.out.println("Введите корректно значение!");
        }
        return wayToFillMap;
    }

    public List<Integer> getAnswerFromUser(String message, int countInputValues){
        boolean hasValidInput = false;
        List<Integer> inputs = new ArrayList<>();
        while(!hasValidInput){
           try{
                System.out.println(message);
                Scanner scanner = new Scanner(System.in);
               String textInput = scanner.nextLine();
               String[] values = textInput.split(",");
               if (values.length != countInputValues) {
                   System.out.println("Введите корректное количество значений!");
                   continue;
               }
               inputs.clear();
               for (String value : values) {
                   inputs.add(Integer.parseInt(value.trim()));
               }
               hasValidInput=true;
           }
           catch(NumberFormatException e){
               System.out.println(MESSAGE_WRONG_INPUT);
           }
        }
        return inputs;
    }

}
