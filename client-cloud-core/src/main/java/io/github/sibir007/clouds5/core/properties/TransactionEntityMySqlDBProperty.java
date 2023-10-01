package io.github.sibir007.clouds5.core.properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * доступ к mysql-db-properties.xml
 * файл для конфигурации Connection к mySql
 */
public class TransactionEntityMySqlDBProperty extends BaseProperty{
    private static Logger logger = LogManager.getLogger();

    private static final String FILE_PROPERTY_NAME = "properties/client-cloud-core/mysql-db-properties.xml";

    private static final TransactionEntityMySqlDBProperty property = new TransactionEntityMySqlDBProperty();

    public static TransactionEntityMySqlDBProperty getSingletonInstance(){
        return property;
    }

    private TransactionEntityMySqlDBProperty(){
        super(FILE_PROPERTY_NAME);
    }




    public String getDbms() {
        return getPropertyForName("dbms");
    }

    public String getUserName() {
        return getPropertyForName("user_name");
    }

    public String getPassword() {
        return getPropertyForName("password");
    }

    public String getServerName() {
        return getPropertyForName("server_name");
    }

    public  String getPortNumber() {
        return getPropertyForName("port_number");
    }


}
