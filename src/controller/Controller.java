package controller;

import domain.PrgState;
import domain.Statement;
import javafx.scene.control.Alert;
import repo.Repository;
import utils.*;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by glinut on 11/4/2016.
 */
public class Controller implements Serializable {
    private ExecutorService executorService;
    private Repository repo;

    public Controller(Repository r) {
        repo = r;
    }

    public void executeAll() throws FileNotFoundException, UnsupportedEncodingException {
        PrgState prg = repo.getCurrent();
        while (!(prg.getExecStack().isEmpty())) {
            executeOneStmt(prg);
            repo.logPrgStateExec();
            prg.setHeap(ConservativeGarbageCollector(prg.getSt(), prg.getHeap()));
        }
        System.out.println(prg);
    }

    public void executeOne() {
        PrgState prgState = repo.getCurrent();
        if (!prgState.getExecStack().isEmpty()) {
            executeOneStmt(prgState);
        } else {
            System.out.println("No more steps to execute");
        }
        System.out.println(prgState);
    }

    public void executeOneStmt(PrgState prg) {
        ExecStack<Statement> stack = prg.getExecStack();
        if (!stack.isEmpty()) {
            Statement statement = stack.pop();
            statement.execute(prg);
        }
    }


    public Heap<Integer, Integer> ConservativeGarbageCollector(SymbolTable<String, Integer> st, Heap<Integer, Integer> heap) {
//         return ((HashMap<Integer,Integer>)heap.getAll()).entrySet()
//                .stream()
//                .filter(s->st.getAll().values().contains(s.getKey()))
//
//                 //.collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue);
//                .collect(Collectors.toMap(
//                e -> e.getKey(),
//                e -> e.getValue().getAverage()
//        ));
        HeapImpl<Integer, Integer> heap2 = new HeapImpl<>();

        Iterator it = ((HeapImpl<Integer, Integer>) heap).all().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> pair = (Map.Entry) it.next();
            if (((SymbolTableImpl) st).getAll().values().contains(pair.getKey())) {
                heap2.add(pair.getKey(), pair.getValue());
            }
        }
        return heap2;

    }

    public List<PrgState> getProgramStates(){
        return repo.getAll();
    }

    public void serialize(String fileName) {
        this.repo.serialize(this.repo.getCurrent(), fileName);
    }

    public void deserialize(String fileName) throws FileNotFoundException, UnsupportedEncodingException {
        this.repo.addPrg(this.repo.deserialize(fileName));
        this.executeAll();
    }

    public List<PrgState> removeCompletedPrgState(List<PrgState> prgStateList) {
        return prgStateList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }

    public void oneStepAllPrg(List<PrgState> l) throws InterruptedException {
        l.forEach(p -> {
            try {
                repo.logPrgStateExec(p);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        List<Callable<PrgState>> callables = l.stream()
                .map((PrgState p) -> (Callable<PrgState>) (() -> p.oneStep()))
                .collect(Collectors.toList());
        List<PrgState> results = executorService.invokeAll(callables)
                .stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException e) {
                        return null;
                    } catch (ExecutionException e) {
                        return null;
                    }
                })
                .filter(p -> p != null)
                .collect(Collectors.toList());
        l.addAll(results);
        repo.setAll(l);
    }

    public void allStep() throws InterruptedException {
        executorService = Executors.newFixedThreadPool(3);
        while (true){
            List<PrgState> prgStates = removeCompletedPrgState(repo.getAll());
            if (prgStates.isEmpty()){
                break;
            }
            oneStepAllPrg(prgStates);
        }

        executorService.shutdownNow();
    }

    public void allStepGUI() {
        executorService = Executors.newFixedThreadPool(2);
        List<PrgState> prgStateList = removeCompletedPrgState(repo.getAll());
        if (prgStateList.size()==0){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Program finished!");
            alert.showAndWait();
            executorService.shutdownNow();
        }
        else {
            try {
                oneStepAllPrg(prgStateList);
            } catch (Exception e) {
                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Program finished!" + e.getMessage());
                alert.showAndWait();
            }
            executorService.shutdownNow();
        }
    }
}
