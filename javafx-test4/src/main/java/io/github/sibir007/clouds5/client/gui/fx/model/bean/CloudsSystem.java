package io.github.sibir007.clouds5.client.gui.fx.model.bean;

import com.sun.javafx.collections.ObservableSetWrapper;
import io.github.sibir007.clouds5.client.core.Cloud;
import javafx.collections.ObservableSet;

import java.util.HashSet;

public class CloudsSystem {

    private static final CloudsSystem cloudsSystem = new CloudsSystem();
    public static CloudsSystem getCloudsSystem() {
        return cloudsSystem;
    }
    private ObservableSet<Cloud> cloudsProperty = new ObservableSetWrapper<>(new HashSet<>());

    private CloudsSystem(){};

    public ObservableSet<Cloud> getCloudsProperty() {
        return cloudsProperty;
    }
    public boolean addCloud(Cloud cloud) {
        return cloudsProperty.add(cloud);
    }

    public boolean removeCloud(Cloud cloud) {
        return cloudsProperty.remove(cloud);
    };
}
