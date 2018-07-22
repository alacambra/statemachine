package tech.lacambra.fabric.statemachine;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.ext.shell.ShellService;
import io.vertx.ext.shell.ShellServiceOptions;
import io.vertx.ext.shell.command.CommandBuilder;
import io.vertx.ext.shell.command.CommandProcess;
import io.vertx.ext.shell.command.CommandRegistry;
import io.vertx.ext.shell.term.TelnetTermOptions;

public class ConsoleCtrl {

    private final Vertx vertx;
    private final CommandRegistry registry;

    public ConsoleCtrl(Vertx vertx) {
        this.vertx = vertx;
        registry = CommandRegistry.getShared(vertx);

    }

    public ConsoleCtrl addCommand(String cmd, Handler<CommandProcess> handler) {
        CommandBuilder builder = CommandBuilder.command(cmd);
        builder.processHandler(process -> {
            handler.handle(process);
            process.end();
        });

        registry.registerCommand(builder.build(vertx));
        return this;
    }

    public ConsoleCtrl addCommand(String cmd, Runnable handler) {
        CommandBuilder builder = CommandBuilder.command(cmd);
        builder.processHandler(process -> {
            handler.run();
            process.end();
        });

        registry.registerCommand(builder.build(vertx));
        return this;
    }

    public ShellService startService() {
        ShellService service = ShellService.create(vertx,
                new ShellServiceOptions().setTelnetOptions(
                        new TelnetTermOptions().
                                setHost("localhost").
                                setPort(4000)
                )
        );
        service.start();
        return service;
    }


}
