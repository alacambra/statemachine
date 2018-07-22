package tech.lacambra.fabric.statemachine;

import io.vertx.core.Vertx;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.statemachine.StateMachine;

public class Runner {

    private static final Vertx vertx = Vertx.vertx();
    private static StateMachine stateMachine;

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("tech.lacambra.fabric.statemachine");
        context.refresh();

        ExStateMachine machine = context.getBeanFactory().getBean(ExStateMachine.class);
        stateMachine = machine.getStateMachine();
        Persister persister = new Persister();

        ConsoleCtrl consoleCtrl = new ConsoleCtrl(vertx);

        consoleCtrl.addCommand("1", () -> stateMachine.sendEvent(ExStateMachine.Events.EVENT1));
        consoleCtrl.addCommand("2", () -> stateMachine.sendEvent(ExStateMachine.Events.EVENT2));
        consoleCtrl.addCommand("p", () -> persister.persist("myFirstSM", stateMachine));
        consoleCtrl.addCommand("l", () -> persister.load(stateMachine));
        consoleCtrl.addCommand("s", (p) -> {
            p.write(stateMachine.getState().getId().toString());
            p.write("\\\n");
        });


        consoleCtrl.startService();
        System.out.println("done!");

    }


}
