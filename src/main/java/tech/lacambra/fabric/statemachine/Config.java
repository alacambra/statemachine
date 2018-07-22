package tech.lacambra.fabric.statemachine;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;

import java.util.EnumSet;
import java.util.logging.Logger;

@Configuration
@EnableStateMachine
public class Config extends EnumStateMachineConfigurerAdapter<ExStateMachine.States, ExStateMachine.Events> {


    public Config() {
        System.out.println("in cfg");
    }

    @Override
    public void configure(StateMachineStateConfigurer<ExStateMachine.States, ExStateMachine.Events> states)
            throws Exception {
        states
                .withStates()
                .initial(ExStateMachine.States.STATE1)
                .states(EnumSet.allOf(ExStateMachine.States.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<ExStateMachine.States, ExStateMachine.Events> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(ExStateMachine.States.STATE1).target(ExStateMachine.States.STATE2)
                .event(ExStateMachine.Events.EVENT1)
                .and()
                .withExternal()
                .guard(new MyGuard())
                .source(ExStateMachine.States.STATE2).target(ExStateMachine.States.STATE1)
                .event(ExStateMachine.Events.EVENT2);
    }

    private static class MyGuard implements Guard<ExStateMachine.States, ExStateMachine.Events> {

        private static final Logger logger = Logger.getLogger(MyGuard.class.getName());

        @Override
        public boolean evaluate(StateContext<ExStateMachine.States, ExStateMachine.Events> context) {
            logger.info("[evaluate] accessing guard");

            return false;
        }
    }
}
