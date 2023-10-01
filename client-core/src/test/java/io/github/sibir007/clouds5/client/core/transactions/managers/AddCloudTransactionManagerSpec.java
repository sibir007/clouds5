package io.github.sibir007.clouds5.client.core.transactions.managers;

import io.github.sibir007.clouds5.client.core.BaseTestCase;
import io.github.sibir007.clouds5.core.Cloud;
import io.github.sibir007.clouds5.core.CloudImpl;
import io.github.sibir007.clouds5.core.services.spi.TransactionEntityService;
import io.github.sibir007.clouds5.core.transactions.AddCloudTransaction;
import io.github.sibir007.clouds5.core.services.spi.CloudsService;
import io.github.sibir007.clouds5.client.core.PostedCloudsClient;
import io.github.sibir007.clouds5.core.transactions.Transaction;
import io.github.sibir007.clouds5.core.transactions.response.TransactionResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

@DisplayName("AddCloudTransactionManager spec testing")
class AddCloudTransactionManagerSpec extends BaseTestCase {
    @Mock
    private PostedCloudsClient cloudsClient;
    @Mock(name = "transactionEntity")
    private TransactionEntityService transactionEntityService;
    @Mock
    private CloudsService cloudsService;
    @InjectMocks
    private AddCloudTransactionManager addCloudTransactionManager;


//    @Disabled
    @Test
    @DisplayName("processTransaction(Cloud cloud) must:" +
            "1. call TransactionEntityService to create AddCloudTransaction" +
            "2. send AddCloudTransaction to CloudsService " +
            "3. receive AddCloudTransactionResponse from CloudsService " +
            "4. save AddCloudTransactionResponse in TransactionEntityService" +
            "5. set CloudAvailabilityStatus to cloud" +
            "6. call cloudClient.addCloud(cloud)" +
            "7. post AddCloudTransactionResponse info to CLoudClient")
    void processTransactionTesting() {
        Cloud cloud = spy(new CloudImpl("10.10.01.10", 4));
        AddCloudTransaction addCloudTransaction = mock(AddCloudTransaction.class);
        TransactionResponse transactionResponse = mock(TransactionResponse.class);
        when(transactionEntityService.createAddCloudTransaction(cloud)).thenReturn(addCloudTransaction);
        when(cloudsService.processAddCloudTransaction(addCloudTransaction)).thenReturn(transactionResponse);
        when(transactionResponse.getStatus()).thenReturn(Transaction.Status.OK);

        addCloudTransactionManager.processTransaction(cloud);

        // verify 1. call TransactionEntityService to create AddCloudTransaction
        verify(transactionEntityService).createAddCloudTransaction(cloud);
        // verify 2. send AddCloudTransaction to CloudsService
        verify(cloudsService).processAddCloudTransaction(addCloudTransaction);
//        // verify 3. receive AddCloudTransactionResponse from CloudsService
//        assertEquals(transactionResponse, transactionResponse1, () -> "should be equal");
        // verify 4. save AddCloudTransactionResponse in TransactionEntityService
        verify(transactionEntityService).saveTransactionResponse(transactionResponse);
        // verify 5.1 set CloudAvailabilityStatus to cloud transaction OK
        verify(cloud).setCloudAvailabilityStatus(Cloud.CloudAvailabilityStatus.OK);
        // verify 6. call cloudClient.addCloud(cloud)
        verify(cloudsClient).addCloud(cloud);
        // verify 7. post AddCloudTransactionResponse info to CLoudClient")
        verify(cloudsClient).postMessage(anyString());
    }


}