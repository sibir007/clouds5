package io.github.sibir007.clouds5.client.core;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ClientControllerTask extends Thread{
    private CloudsClient cloudsClient;

    private Queue<Transaction> transactionQueue = new ConcurrentLinkedQueue<>();

    public ClientControllerTask(){
        super("ClientControllerTask");
    }


}
