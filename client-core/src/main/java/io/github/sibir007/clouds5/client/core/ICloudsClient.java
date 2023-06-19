package io.github.sibir007.clouds5.client.core;

import java.io.File;
import java.util.List;

public interface ICloudsClient {
    void postMessage(String msg);

    void setClientController(ClientController clientController);
    void ls(String path, List<File>)
}
