package io.github.sibir007.clouds5.client.core;

import java.util.Set;
import java.util.UUID;

public class CloudImpl implements Cloud {
    private UUID id;
    private String url;
    private int port;
    private Set<Account> accounts;

    CloudImpl(String url, int port){
        this.url = url;
        this.port = port;
        id = UUID.randomUUID();
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString(){
        return "[Cloud /n"
                + "id: " + getId() + "/n"
                + "url" + getUrl() + "/n"
                + "port" + getPort() + "/n"
                + "accounts: /n"
                +
                "]";
    }



}
