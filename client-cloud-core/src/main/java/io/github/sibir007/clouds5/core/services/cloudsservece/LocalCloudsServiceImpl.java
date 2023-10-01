package io.github.sibir007.clouds5.core.services.cloudsservece;

import io.github.sibir007.clouds5.core.services.spi.CloudsService;
import io.github.sibir007.clouds5.core.transactions.AddCloudTransaction;
import io.github.sibir007.clouds5.core.transactions.response.TransactionResponse;

//Класс заглушка. В случае если CloudsServiceProvider не находит нужную реализацию - вставляет его.
//Исключительно для целей разработки
public class LocalCloudsServiceImpl implements CloudsService {
    private static final LocalCloudsServiceImpl cloudsService = new LocalCloudsServiceImpl();
    public static CloudsService getSingleton(){
        return cloudsService;
    }
    @Override
    public TransactionResponse processAddCloudTransaction(AddCloudTransaction addCloudTransaction) {
        return null;
    }
}
