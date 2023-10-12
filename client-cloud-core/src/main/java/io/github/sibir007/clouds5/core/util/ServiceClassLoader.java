package io.github.sibir007.clouds5.core.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.print.DocFlavor;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.net.URL;
import java.util.function.Supplier;

public class ServiceClassLoader {
    private static Logger logger = LogManager.getLogger();

    /**
     * Scans all classes accessible from the context class loader which belong to the given package.
     *
     * @param serviceProvidersPackageName The base package
     * @return The classes
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static  <T> List<Provider<T>> getClasses(Class<T> serviceInterface, String serviceProvidersPackageName) throws ClassNotFoundException, IOException {
//        String packageName = "io.github.sibir007.clouds5.core.services.transactionsentity.db.spi";
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = serviceProvidersPackageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
//            logger.info("");
//            System.out.println(resource);
        }
        ArrayList<Provider<T>> providers = new ArrayList<Provider<T>>();
        for (File directory : dirs) {
            if (directory.exists() & directory.isDirectory()) {
                File[] files = directory.listFiles();
                for (File file : files) {
                    if (!file.isDirectory()) {
                        if (file.getName().endsWith(".class")) {
                            Class<?> tClass = Class.forName(serviceProvidersPackageName + '.' + file.getName().substring(0, file.getName().length() - 6));
                            logger.trace("file candidate " + tClass.getName());

                            Class<?>[] interfaces = tClass.getInterfaces();
                            for (Class<?> anInterface : interfaces) {
                                if (serviceInterface.equals(anInterface)) {
                                    providers.add(new Provider(tClass));
                                    logger.info("added class " + tClass);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return providers;
    }

    public static class Provider<T>{
        //class for instance
        private final Class<T> tClass;
        public Provider(Class<T> tClass){
            this.tClass = tClass;
        }
        public Class<T> type(){
            return tClass;
        }

        public T getInstance() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
            return (T)tClass.getConstructor(new Class[0]).newInstance();
        }
    }
}
