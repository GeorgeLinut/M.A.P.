package view;

import controller.Controller;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * Created by glinut on 11/29/2016.
 */
public class DeserializeProgramStateCommand extends Command {
    private Controller controller;
    public DeserializeProgramStateCommand(String key, String description,Controller controller) {
        super(key, description);
        this.controller=controller;
    }

    @Override
    public void execute() throws FileNotFoundException, UnsupportedEncodingException {
        Scanner sc= new Scanner(System.in);
        String file = sc.nextLine();
        this.controller.deserialize(file);
    }
}
