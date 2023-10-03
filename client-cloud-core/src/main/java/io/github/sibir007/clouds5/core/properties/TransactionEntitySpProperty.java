package io.github.sibir007.clouds5.core.properties;

import io.github.sibir007.clouds5.core.services.spi.TransactionEntityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TransactionEntitySpProperty extends BaseProperty{
    private static Logger logger = LogManager.getLogger();

    private static final String FILE_PROPERTY_NAME = "transaction-entity-sp-properties.xml";

    private static final TransactionEntitySpProperty property = new TransactionEntitySpProperty();

    public static TransactionEntitySpProperty getSingletonInstance(){
        return property;
    }

    private TransactionEntitySpProperty(){
        super(FILE_PROPERTY_NAME);
    }


    public String getTransactionsEntityService(){
        return getPropertyForName("transactions_entity_service");
    }
}
