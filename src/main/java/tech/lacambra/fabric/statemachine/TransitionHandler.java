package tech.lacambra.fabric.statemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

import java.util.logging.Logger;

@WithStateMachine
public class TransitionHandler {

    @Autowired
    private StateMachine<ExStateMachine.States, ExStateMachine.Events> stateMachine;

    private static final Logger logger = Logger.getLogger(TransitionHandler.class.getName());

    public TransitionHandler() {
        System.out.println("in");
    }

    @OnTransition(target = "STATE1")
    public void toState1() {
        logger.info("[toState1] ");
        stateMachine.sendEvent(ExStateMachine.Events.EVENT2);
    }

    @OnTransition(target = "STATE2")
    public void toState2() {
        logger.info("[toState2] ");
        stateMachine.sendEvent(ExStateMachine.Events.EVENT1);
    }
}
