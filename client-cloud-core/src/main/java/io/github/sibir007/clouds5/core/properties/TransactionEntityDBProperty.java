package io.github.sibir007.clouds5.core.properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TransactionEntityDBProperty extends BaseProperty{
    private static Logger logger = LogManager.getLogger();

    private static final String FILE_PROPERTY_NAME = "properties/client-cloud-core/transaction-entity-db-properties.xml";
    private static final TransactionEntityDBProperty property = new TransactionEntityDBProperty();

    public static TransactionEntityDBProperty getSingletonInstance(){
        return property;
    }


    private TransactionEntityDBProperty(){
        super(FILE_PROPERTY_NAME);
    };




    public String getDbms(){
        return getPropertyForName("dbms");
    }

    public String getDatabaseName(){
        return getPropertyForName("database_name");
    }



}
