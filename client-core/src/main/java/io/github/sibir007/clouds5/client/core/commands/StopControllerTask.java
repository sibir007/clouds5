package io.github.sibir007.clouds5.client.core.commands;

public class StopControllerTask implements CloudTransactionTask {
    private final CommandType commandType = CommandType.STOP_CONTROLLER;

    private static StopControllerTask command = new StopControllerTask();

    public static final StopControllerTask COMMAND(){
        return command;
    }
    private StopControllerTask(){}
    public CommandType commandType(){
        return commandType;
    }

}
