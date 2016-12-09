package view;

import controller.Controller;
import exceptions.InterpretorException;

import java.util.Scanner;

/**
 * Created by glinut on 11/28/2016.
 */
public class SerializeProgramStateCommand extends Command {
    private Controller ctrl;

    public SerializeProgramStateCommand(String key, String description,Controller controller) {
        super(key, description);
        this.ctrl=controller;
    }

    @Override
    public void execute() {
        try{
            Scanner sc = new Scanner(System.in);
            String file = sc.nextLine();
            this.ctrl.serialize(file);
        }
        catch (Exception e){
            throw new InterpretorException(e.getMessage());
        }

    }
}
