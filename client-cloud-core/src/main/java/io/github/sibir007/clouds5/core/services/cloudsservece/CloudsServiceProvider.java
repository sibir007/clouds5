package io.github.sibir007.clouds5.core.services.cloudsservece;

import io.github.sibir007.clouds5.core.services.spi.CloudsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.ServiceLoader;

public class CloudsServiceProvider {
    private static  final Logger logger = LogManager.getLogger();
    private static CloudsService cloudsService;
    public static synchronized CloudsService gerCloudsService(){
            if (cloudsService == null){
                ServiceLoader<CloudsService> loader = ServiceLoader.load(CloudsService.class);
                Optional<ServiceLoader.Provider<CloudsService>> optional;
                optional = loader.stream().map(p-> {
                    logger.trace("CloudsService found" + p.type());
                    return p;
                }).filter(p -> p.type().getName().equals(gerCloudsServiceClassName())).findAny();
                if(optional.isPresent()){
                    cloudsService = optional.get().get();
                }else {
                    cloudsService = LocalCloudsServiceImpl.getSingleton();
                }
            }
        return cloudsService;
    }

    private static String gerCloudsServiceClassName() {

        return null;
    }
}
