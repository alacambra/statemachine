package tech.lacambra.fabric.statemachine;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.json.JsonObject;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.support.DefaultStateMachineContext;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class FileStateMachinePersist implements StateMachinePersist<ExStateMachine.States, ExStateMachine.Events, String> {

    private static final Logger logger = Logger.getLogger(FileStateMachinePersist.class.getName());
    private static final ObjectMapper OBJECT_MAPPER;
    private String persistedContext = "";

    static {
        OBJECT_MAPPER = new ObjectMapper();
    }

    @Override
    public void write(StateMachineContext<ExStateMachine.States, ExStateMachine.Events> context, String contextObj) throws Exception {
        logger.info("[write] " + context);
        logger.info("[write] " + new String(contextObj.getBytes(Charset.forName("UTF-8"))));
        SerializedStateMachine serializedStateMachine = new SerializedStateMachine();
        serializedStateMachine.setId(contextObj);
        serializedStateMachine.setStateMachineContext(context);
        persistedContext = OBJECT_MAPPER.writeValueAsString(serializedStateMachine);
        logger.info("[write] " + persistedContext);

        Path path = Paths.get("/Users/albertlacambra1/junk/sm.json");
        Files.write(path, persistedContext.getBytes());

    }

    @Override
    public StateMachineContext<ExStateMachine.States, ExStateMachine.Events> read(String contextObj) throws Exception {
        persistedContext = new String(Files.readAllBytes(Paths.get("/Users/albertlacambra1/junk/sm.json")));
        JsonObject jsonObject = new JsonObject(persistedContext);
        String state = jsonObject.getJsonObject("stateMachineContext").getString("state");
        StateMachineContext<ExStateMachine.States, ExStateMachine.Events> c = new DefaultStateMachineContext<>(
                ExStateMachine.States.valueOf(state),
                null,
                null,
                null
        );

        logger.info("[read] " + c);
        Path path = Paths.get("/Users/albertlacambra1/junk/sm.json");

        return c;
    }
}
