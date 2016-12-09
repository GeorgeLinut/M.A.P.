package view;

import controller.Controller;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 * Created by glinut on 11/15/2016.
 */
public class RunExampleCommand extends Command {
    private Controller controller;
    public RunExampleCommand(String key, String description,Controller ctrl) {
        super(key, description);
        this.controller=ctrl;
    }

    @Override
    public void execute() {
        try {
            controller.executeAll();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
