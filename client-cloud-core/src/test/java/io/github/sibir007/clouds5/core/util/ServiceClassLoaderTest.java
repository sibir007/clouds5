package io.github.sibir007.clouds5.core.util;

import io.github.sibir007.clouds5.core.services.transactionsentity.db.test_spi.TestGetTransactionForIdService;
import io.github.sibir007.clouds5.core.util.ServiceClassLoader.Provider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServiceClassLoaderTest {
    private static Logger logger = LogManager.getLogger();

    /**
     * Тестирование ServiceClassLoader.
     * ServiceClassLoader.getClasses(Interface, Package) принимает Interface сервиса и пакет в котором расположены
     * ServiceProviders - классы имплементирующие Interface.
     * Возвращает List<Provider<Class<Interface>>>, Provider обёртка над классом имплементирующим Interface,
     * provider.type() возвращает клас имплементирующим Interface, provider.gerInstance() возвращает экземпляр класса
     * тест в тестовом пакете находит все классы имплементирующие тестовый интерфейс и проверяет, что все они имплементируют
     * интерфейс. Затем проверяем что provider.gerInstance() возвращает экземпляр класса и тип экземпляра
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    @DisplayName("test ServiceClassLoader")
    void testLoader() throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Class<TestGetTransactionForIdService> testGetTransactionForIdServiceClass = TestGetTransactionForIdService.class;
        List<Provider<TestGetTransactionForIdService>> providers = ServiceClassLoader.getClasses(testGetTransactionForIdServiceClass, "io.github.sibir007.clouds5.core.services.transactionsentity.db.test_spi");

//        providers.stream().forEach(provider -> logger.info("loaded service "+provider.type().getName()));
        //проверка что ServiceClassLoader загрузил классы имплементирующие соответствующий интерфейс
        providers.stream().forEach(provider -> {
            assertTrue(implementsInterface(provider.type(), testGetTransactionForIdServiceClass), "should be implements interface");
        });

        TestGetTransactionForIdService obj = (TestGetTransactionForIdService)providers.get(0).getInstance();
        assertNotNull(obj, "should be non null");

    }

    private boolean implementsInterface(Class<TestGetTransactionForIdService> type, Class<TestGetTransactionForIdService> testGetTransactionForIdServiceClass) {
        boolean check = false;
        Class<?>[] classes = type.getInterfaces();
        for (Class<?> cls: classes) {
            if (cls.equals(testGetTransactionForIdServiceClass)) {
                check = true;
                break;
            }
        }
        return check;
    }

}