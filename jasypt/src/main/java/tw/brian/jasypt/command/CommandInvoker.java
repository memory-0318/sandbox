package tw.brian.jasypt.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import tw.brian.jasypt.BaseCommand;
import tw.brian.jasypt.command.listener.CommandListener;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2020/12/11
 */
@Component
@Slf4j
public class CommandInvoker {
    public void execute(@NotNull(message = "The input command must not be null") BaseCommand command) {
        this.execute(command, null);
    }

    public void execute(
        @NotNull(message = "The input command must not be null") BaseCommand command,
        CommandListener commandListener) {
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();

        this.beforeExecute(command, commandListener);
        try {
            command.execute();
        } catch (IOException e) {
            log.error("Exception occurred when executing command");
        }
        this.afterExecute(command, commandListener);

        stopWatch.stop();
    }

    protected void beforeExecute(BaseCommand command, CommandListener commandListener) {
        if (Objects.nonNull(commandListener)) {
            commandListener.beforeExecute(command);
        }
    }

    protected void afterExecute(BaseCommand command, CommandListener commandListener) {
        if (Objects.nonNull(commandListener)) {
            commandListener.afterExecute(command);
        }
    }
}
