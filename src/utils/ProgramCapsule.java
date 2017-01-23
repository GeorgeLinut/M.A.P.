package utils;

import controller.Controller;
import domain.PrgState;

/**
 * Created by glinut on 1/20/2017.
 */
public class ProgramCapsule {
    Integer programId;
    Controller controller;
    String initialView;
    PrgState prgState;

    public ProgramCapsule(Integer programId, Controller controller, PrgState prgState) {
        this.programId = new Integer(programId);
        this.initialView = prgState.getExecStack().top().toString();
        this.controller = controller;
        this.prgState = prgState;
    }

    public Integer getProgramId() {
        return programId;
    }

    public Controller getController() {
        return controller;
    }

    public String getInitialView() {
        return initialView;
    }

    public PrgState getPrgState() {
        return prgState;
    }

    public String toString(){
        return getInitialView() +"\n";
    }
}
