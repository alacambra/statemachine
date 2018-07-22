package tech.lacambra.fabric.statemachine;

import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

public class Persister {

    private FileStateMachinePersist fileStateMachinePersist;
    private StateMachinePersister<ExStateMachine.States, ExStateMachine.Events, String> stateMachinePersister;

    public Persister() {
        fileStateMachinePersist = new FileStateMachinePersist();
        stateMachinePersister = new DefaultStateMachinePersister(fileStateMachinePersist);
    }

    public void persist(String id, StateMachine stateMachine) {
        try {
            stateMachinePersister.persist(stateMachine, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void load(StateMachine stateMachine) {
        try {
            stateMachinePersister.restore(stateMachine, "");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
