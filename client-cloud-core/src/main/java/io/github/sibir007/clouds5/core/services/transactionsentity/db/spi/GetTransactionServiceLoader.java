package io.github.sibir007.clouds5.core.services.transactionsentity.db.spi;

import io.github.sibir007.clouds5.core.transactions.Transaction;
import io.github.sibir007.clouds5.core.transactions.Transaction.TransactionType;
import io.github.sibir007.clouds5.core.util.ServiceClassLoader;
import io.github.sibir007.clouds5.core.util.ServiceClassLoader.Provider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.*;

public class GetTransactionServiceLoader {
    private static Logger logger = LogManager.getLogger();
    private static Map<TransactionType, GetTransactionForIdService> serviceMap = new HashMap<>();

    static {
        List<Provider<GetTransactionForIdService>> providerList;
        try {
            providerList = ServiceClassLoader.getClasses(
                    GetTransactionForIdService.class,
                    "io.github.sibir007.clouds5.core.services.transactionsentity.db.spi");
        } catch (ClassNotFoundException | IOException e) {
            logger.error("exception in ServiceClassLoader.getClasses()");
            throw new RuntimeException(e);
        }
        providerList.forEach(provider -> {
            Class<GetTransactionForIdService> clazz = provider.type();
            Annotation[] annotations = clazz.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof TransactionTypeAnnotation){
                    try {
                        serviceMap.put(((TransactionTypeAnnotation) annotation).value(), provider.getInstance());
                        logger.info("added class in TransactionForIdService " + provider.type());
                    } catch (NoSuchMethodException |
                             IllegalAccessException |
                             InstantiationException |
                             InvocationTargetException e) {
                        logger.error("exception in provider.getInstance()");
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    public static synchronized Optional<GetTransactionForIdService> getTransactionForIdService(TransactionType transactionType, Connection connection) {
        GetTransactionForIdService service = serviceMap.get(transactionType);
        if (service != null) {
            service.setConnection(connection);
        }
        Optional<GetTransactionForIdService> optional = Optional.ofNullable(service);
        return optional;
    }
}
