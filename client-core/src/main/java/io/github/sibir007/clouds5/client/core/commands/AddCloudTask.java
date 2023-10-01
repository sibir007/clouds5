package io.github.sibir007.clouds5.client.core.commands;

import io.github.sibir007.clouds5.core.Cloud;

public class AddCloudTask implements CloudTransactionTask {
    private final CommandType commandType = CommandType.ADD_CLOUD;

    public static final AddCloudTask COMMAND(Cloud cloud){
        AddCloudTask command = new AddCloudTask();
        command.setCloud(cloud);
        return command;
    }
    private Cloud cloud;
    private AddCloudTask(){}

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
