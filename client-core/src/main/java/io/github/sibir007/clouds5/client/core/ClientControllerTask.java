package io.github.sibir007.clouds5.client.core;

import io.github.sibir007.clouds5.client.core.CloudsClient;
import io.github.sibir007.clouds5.client.core.Transaction;
import io.github.sibir007.clouds5.client.core.commands.CloudTransactionTask;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ClientControllerTask extends Thread{
    private CloudsClient cloudsClient;



    public ClientControllerTask(){
        super("ClientControllerTask");
    }

    @Override
    public void run(){

    }


}
