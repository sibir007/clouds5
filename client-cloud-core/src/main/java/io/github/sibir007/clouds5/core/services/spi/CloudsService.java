package io.github.sibir007.clouds5.core.services.spi;

import io.github.sibir007.clouds5.core.transactions.AddCloudTransaction;
import io.github.sibir007.clouds5.core.transactions.response.TransactionResponse;

public interface CloudsService {
    /**
     * Для разработки.
     * Возвращает локальный вариант CloudsService,
     * т.е. все операции в Cloud-ах будут выполняться на локальной
     * машине. Для продакшен использовать CloudServiceWrapper.
     * @return CloudsService
     */
    static CloudsService getCloudsServiceLocalSingleton() {
        return null;
    }

    /**
     * Для продакшен.
     * Возвращает обёртку клиентского цокета по которому
     * будет происходить сетевое взаимодействие с удалёнными Cloud-ами
     * @return CloudsService
     */
    static CloudsService getCloudsServiceWrapperSingleton() {
        return null;
    }

    /**
     * Для удобства.
     * Должен возвращать имплементацию CloudsService в зависимость
     * от настроек указанных во внешнем файле. Т.е. имплементации в
     * зависимость от текущей потребности девелопмент/продакшен и т.п.
     * @return CloudsService
     */
    // TODO: 10.09.2023 сделать реализацию запроса настроек
    //  из внешнего файла и в зависимости от этих настроек
    //  выбор имплементации CloudsService

    static CloudsService getCloudsServiceSingletonForExternalSetting(){
        return getCloudsServiceLocalSingleton();
    }


    TransactionResponse processAddCloudTransaction(AddCloudTransaction addCloudTransaction);
}
