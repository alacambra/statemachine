package tech.lacambra.fabric.statemachine;

import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineContext;

public class SerializedStateMachine {

    String id;
    StateMachineContext stateMachineContext;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StateMachineContext getStateMachineContext() {
        return stateMachineContext;
    }

    public void setStateMachineContext(StateMachineContext stateMachineContext) {
        this.stateMachineContext = stateMachineContext;
    }
}
