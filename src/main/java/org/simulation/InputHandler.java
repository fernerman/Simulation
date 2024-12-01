package main.java.org.simulation.Constant;

import main.java.org.simulation.Coordinates;
import main.java.org.simulation.Entities.*;
import main.java.org.simulation.MapSimulation;
import main.java.org.simulation.Renderer;
import main.java.org.simulation.Simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputHandler {
    boolean isExitFromCreatingMap = false;
    private Renderer renderer;
    private Scanner scanner;
    private Simulation simulation;
    public final static String MESSAGE_GREETING = "Добро пожаловать в \"Симуляцию\"!";
    public final static String MESSAGE_MAP_FILLING = "Каким образом хотите заполнить карту?\n1. Самостоятельно\n2. Случайным образом.";
    public final static String MESSAGE_INPUT_VALUES_OF_COORDINATES = "Введите через запятую координаты (X,Y):";
    public final static String MESSAGE_WRONG_INPUT = "Введите корректно значение!";
    public final static String MESSAGE_ACTION_ON_MAP = "1. Добавить существо.\n2. Удалить существо.";
    public final static String MESSAGE_CHOOSE_ENTITY = "Выберете существо\n1. Хищник\n2. Травоядное\n3. Трава\n4. Скала\n5. Дерево.";
    public final static String MESSAGE_CONTINUE_CHANGING_MAP = "Хотите продолжить менять карту?.\n1. Да.\n2. Нет.";

    public final static String MESSAGE_INPUT_VALUES_OF_PREDATOR = "Для хищника через запятую укажите силу атаки и скорость передвижения по клеткам.";
    public final static String MESSAGE_INPUT_VALUES_OF_HERBIVORE = "Для травоядного через запятую укажите количество HP и скорость передвижения по клеткам";
    public final static String MESSAGE_CHOOSE_ACTION_IN_GAME = "1.Отсимулировать ход в симуляции\n2. 2.Выйти из игры.";
    public final static int RECEIVED_ONE_VALUE = 1;
    public final static int RECEIVED_TWO_VALUES = 2;

    public final static int ADD_ENTITY = 1;
    public final static int DELETE_ENTITY = 2;

    public final static int PREDATOR = 1;
    public final static int HERBIVORE = 2;
    public final static int GRASS = 3;
    public final static int ROCK = 4;
    public final static int TREE = 5;

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    public InputHandler(Scanner scanner, Renderer renderer) {
        this.scanner=scanner;
        this.renderer=renderer;
    }

    public void startListening() {
        new Thread(() -> {
            while (true) {
                String input = scanner.nextLine();
                if (" ".equals(input)) {
                    simulation.togglePause();
                } else {
                    System.out.println("Введите пробел для паузы/продолжения.");
                }
            }
        }).start();
    }

    public void fillMapSimulationByUser(MapSimulation map) throws InterruptedException {
        map.addEmptyCells();
        //changing map from user
        isExitFromCreatingMap = false;
        while (!isExitFromCreatingMap) {
            int action = getAnswerFromUser(MESSAGE_ACTION_ON_MAP,
                    RECEIVED_ONE_VALUE).getFirst();
            renderer.render(map);
            List<Integer> chosenCoordinatesForChangingEntity = getAnswerFromUser(MESSAGE_INPUT_VALUES_OF_COORDINATES,
                    RECEIVED_TWO_VALUES);
            int x = chosenCoordinatesForChangingEntity.getFirst();
            int y = chosenCoordinatesForChangingEntity.getLast();
            switch (action) {
                case ADD_ENTITY:
                    int chosenEntity = getAnswerFromUser(MESSAGE_CHOOSE_ENTITY,
                            RECEIVED_ONE_VALUE).getFirst();
                    switch (chosenEntity) {
                        case PREDATOR:
                            List<Integer> chosenValuesOfPropertiesPredator = getAnswerFromUser(MESSAGE_INPUT_VALUES_OF_PREDATOR,
                                    RECEIVED_TWO_VALUES);
                            map.addEntity(new Coordinates(x, y), new Predator(chosenValuesOfPropertiesPredator.getFirst(),
                                    chosenValuesOfPropertiesPredator.getLast()));
                            break;
                        case HERBIVORE:
                            List<Integer> chosenValuesOfPropertiesHerbivore = getAnswerFromUser(
                                    MESSAGE_INPUT_VALUES_OF_HERBIVORE,
                                    RECEIVED_TWO_VALUES);
                            map.addEntity(new Coordinates(x, y), new Herbivore(chosenValuesOfPropertiesHerbivore.getFirst(), chosenValuesOfPropertiesHerbivore.getLast()));
                            break;
                        case InputHandler.GRASS:
                            map.addEntity(new Coordinates(x, y), new Grass());
                            break;
                        case InputHandler.ROCK:
                            map.addEntity(new Coordinates(x, y), new Rock());
                            break;
                        case InputHandler.TREE:
                            map.addEntity(new Coordinates(x, y), new Tree());
                            break;
                    }
                    break;
                case InputHandler.DELETE_ENTITY:
                    map.removeEntity(new Coordinates(x, y));
                    break;
            }
            renderer.render(map);
            int chosenAnswerContinueChangingMap = getAnswerFromUser(
                    InputHandler.MESSAGE_CONTINUE_CHANGING_MAP,
                    InputHandler.RECEIVED_ONE_VALUE).getFirst();
            if (chosenAnswerContinueChangingMap == 2) {
                isExitFromCreatingMap = true;
            }
        }

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
