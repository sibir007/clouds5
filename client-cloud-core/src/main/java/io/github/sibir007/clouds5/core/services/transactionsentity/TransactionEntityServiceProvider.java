package io.github.sibir007.clouds5.core.services.transactionsentity;

import io.github.sibir007.clouds5.core.properties.TransactionEntitySpProperty;
import io.github.sibir007.clouds5.core.services.spi.TransactionEntityService;
import io.github.sibir007.clouds5.core.services.transactionsentity.db.DatabaseWrapperTransactionsEntityServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.ServiceLoader;

public class TransactionEntityServiceProvider {
    private static TransactionEntityService transactionEntityService;
    private static  final Logger logger = LogManager.getLogger();



    public static synchronized TransactionEntityService getTransactionEntityService(){
        if (transactionEntityService == null){
            ServiceLoader<TransactionEntityService> loader = ServiceLoader.load(TransactionEntityService.class);
            Optional<ServiceLoader.Provider<TransactionEntityService>> optional;
            optional = loader.stream().map(p-> {
                logger.trace("TransactionEntityService found" + p.type());
                return p;
            }).filter(p -> p.type().getName().equals(gerTransactionEntityServiceName())).findAny();
            if(optional.isPresent()){
                transactionEntityService = optional.get().get();
            }else {
                transactionEntityService = DatabaseWrapperTransactionsEntityServiceImpl.getTransactionEntityServiceSingleton();
            }
        }
        return transactionEntityService;
    }

    private static String gerTransactionEntityServiceName() {

        return TransactionEntitySpProperty.getTransactionsEntityService();
    }


}
