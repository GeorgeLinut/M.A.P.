package view;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by glinut on 11/15/2016.
 */
public class TextMenu {
    private HashMap<String, Command> commandHashMap;

    public TextMenu() {
        commandHashMap = new HashMap<>();
    }

    public void addCommand(Command command) {
        this.commandHashMap.put(command.getKey(), command);
    }

    public void printMenu() {
        for (Command command : commandHashMap.values()) {
            String line = String.format("%4s:%s", command.getKey(), command.getDescription());
            System.out.println(line);
        }
    }

    public void show() throws FileNotFoundException, UnsupportedEncodingException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            System.out.printf("Input your option: ");
            String key = scanner.nextLine();
            Command command = commandHashMap.get(key);
            if (command == null) {
                System.out.println("Invalid Option");
                continue;
            }
            command.execute();
        }
    }


}
