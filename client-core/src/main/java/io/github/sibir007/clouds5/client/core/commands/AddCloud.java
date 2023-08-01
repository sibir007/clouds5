package io.github.sibir007.clouds5.client.core.commands;

import io.github.sibir007.clouds5.client.core.Cloud;

public class AddCloud implements CloudTransactionTask {
    private final CommandType commandType = CommandType.ADD_CLOUD;
    private static AddCloud command = new AddCloud();

    public static final AddCloud COMMAND(Cloud cloud){
        command.setCloud(cloud);
        return command;
    }
    private Cloud cloud;
    private AddCloud(){}

    public Cloud getCloud() {
        return cloud;
    }
    private void setCloud(Cloud cloud){
        this.cloud = cloud;
    }

    public CommandType commandType(){
        return commandType;
    }

}
