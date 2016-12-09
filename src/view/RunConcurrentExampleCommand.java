package view;

import controller.Controller;



/**
 * Created by glinut on 12/3/2016.
 */
public class RunConcurrentExampleCommand extends Command {
    private Controller controller;
    public RunConcurrentExampleCommand(String key, String description,Controller ctrl) {
        super(key, description);
        this.controller=ctrl;
    }

    @Override
    public void execute() {
        try {
            controller.allStep();
        }  catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

