package tech.lacambra.fabric.statemachine;

import io.vertx.ext.shell.ShellService;
import io.vertx.ext.shell.ShellServiceOptions;
import io.vertx.ext.shell.command.CommandBuilder;
import io.vertx.ext.shell.command.CommandRegistry;
import io.vertx.ext.shell.term.TelnetTermOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.EnumSet;

@Component
public class ExStateMachine {

    @Autowired
    @Qualifier("stateChartModel")
    private String stateChartModel;

    enum States {
        STATE1, STATE2
    }

    enum Events {
        EVENT1, EVENT2
    }

    @Autowired
    private StateMachine<States, Events> stateMachine;


    public ExStateMachine() {
    }

    @PostConstruct
    public void init() {
        stateMachine.start();
    }

    void doSignals() {
        stateMachine.start();
        stateMachine.sendEvent(Events.EVENT1);
        stateMachine.sendEvent(Events.EVENT2);
    }

    public org.springframework.statemachine.StateMachine<States, Events> buildMachine() throws Exception {
        StateMachineBuilder.Builder<States, Events> builder = StateMachineBuilder.builder();

        builder.configureStates()
                .withStates()
                .initial(States.STATE1)
                .states(EnumSet.allOf(States.class));

        builder.configureTransitions()
                .withExternal()
                .source(States.STATE1).target(States.STATE2)
                .event(Events.EVENT1)
                .and()
                .withExternal()
                .source(States.STATE2).target(States.STATE1)
                .event(Events.EVENT2);

        return builder.build();
    }

    public StateMachine<States, Events> getStateMachine() {
        return stateMachine;
    }

    public String getStateChartModel() {
        return stateChartModel;
    }
}