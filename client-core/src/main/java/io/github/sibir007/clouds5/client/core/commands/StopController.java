package io.github.sibir007.clouds5.client.core.commands;

import io.github.sibir007.clouds5.client.core.Cloud;

public class StopController implements CloudTransactionTask {
    private final CommandType commandType = CommandType.STOP_CONTROLLER;

    private static StopController command = new StopController();

    public static final StopController COMMAND(){
        return command;
    }
    private StopController(){}
    public CommandType commandType(){
        return commandType;
    }

}
