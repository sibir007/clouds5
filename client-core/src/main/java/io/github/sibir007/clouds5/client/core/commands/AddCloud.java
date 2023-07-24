package io.github.sibir007.clouds5.client.core.commands;

import io.github.sibir007.clouds5.client.core.Cloud;

public class AddCloud implements CloudTransactionTask {

    public static final AddCloud COMMAND(Cloud cloud){
        return new AddCloud(cloud);
    }
    private Cloud cloud;
    private AddCloud(Cloud cloud){
        this.cloud = cloud;
    }

    public Cloud getCloud() {
        return cloud;
    }

}
