package io.github.sibir007.clouds5.core.properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * доступ к sqlite-db-properties.xml
 * файл для конфигурации Connection к Sqlite
 */
public class TransactionEntitySqliteDBProperty extends BaseProperty {
    private static Logger logger = LogManager.getLogger();

    private static final String FILE_PROPERTY_NAME = "sqlite-db-properties.xml";

    private static final TransactionEntitySqliteDBProperty property = new TransactionEntitySqliteDBProperty();

    public static TransactionEntitySqliteDBProperty getSingletonInstance(){
        return property;
    }
    private TransactionEntitySqliteDBProperty(){
        super(FILE_PROPERTY_NAME);
    }


    public String getDbms() {
        return getPropertyForName("dbms");
    }
}
