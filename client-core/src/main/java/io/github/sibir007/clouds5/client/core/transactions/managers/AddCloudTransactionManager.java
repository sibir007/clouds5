package io.github.sibir007.clouds5.client.core.transactions.managers;

import io.github.sibir007.clouds5.core.services.cloudsservece.CloudsServiceProvider;
import io.github.sibir007.clouds5.core.Cloud;
import io.github.sibir007.clouds5.client.core.PostedCloudsClient;
import io.github.sibir007.clouds5.core.services.spi.TransactionEntityService;
import io.github.sibir007.clouds5.core.services.transactionsentity.TransactionEntityServiceProvider;
import io.github.sibir007.clouds5.core.transactions.AddCloudTransaction;
import io.github.sibir007.clouds5.core.transactions.Transaction;
import io.github.sibir007.clouds5.core.services.spi.CloudsService;
import io.github.sibir007.clouds5.core.transactions.response.TransactionResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;


public class AddCloudTransactionManager {
    private static final AddCloudTransactionManager manager = new AddCloudTransactionManager();
    private static Logger logger = LogManager.getLogger();
    private PostedCloudsClient cloudsClient;
    private TransactionEntityService transactionEntity = TransactionEntityServiceProvider.getTransactionEntityService();
    private CloudsService cloudsService = CloudsServiceProvider.gerCloudsService();


    private AddCloudTransactionManager() {
    }

    public static AddCloudTransactionManager getAddCloudTransactionManagerSingleton() {
        return manager;
    }

    public synchronized void processTransaction(Cloud cloud) {
        logger.trace("processTransaction(Cloud cloud)");
        AddCloudTransaction addCloudTransaction;
        TransactionResponse response;
        try {
             addCloudTransaction = transactionEntity.createAddCloudTransaction(cloud);
             response = cloudsService.processAddCloudTransaction(addCloudTransaction);
            transactionEntity.saveTransactionResponse(response);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        if (response.getStatus() == Transaction.Status.OK) {
            cloud.setCloudAvailabilityStatus(Cloud.CloudAvailabilityStatus.OK);
        } else {
            cloud.setCloudAvailabilityStatus(Cloud.CloudAvailabilityStatus.FILED);
        }
        cloudsClient.postMessage(Transaction.reportMessage(addCloudTransaction));
        cloudsClient.addCloud(cloud);
    }

    public void setCloudsClient(PostedCloudsClient cloudsClient) {
        this.cloudsClient = cloudsClient;
    }


}
