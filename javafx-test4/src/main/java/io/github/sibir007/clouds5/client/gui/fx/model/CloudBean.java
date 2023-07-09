package io.github.sibir007.clouds5.client.gui.fx.model;

import com.sun.javafx.collections.ObservableSetWrapper;
import io.github.sibir007.clouds5.client.core.Account;
import io.github.sibir007.clouds5.client.core.Cloud;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableStringValue;
import javafx.collections.ObservableSet;

import java.util.HashSet;

public class CloudBean implements Cloud {
    private final SimpleStringProperty urlProperty = new SimpleStringProperty();
    private final SimpleIntegerProperty portProperty = new SimpleIntegerProperty();
    private final ObservableSet<Account> accounts = new ObservableSetWrapper(new HashSet());

    public CloudBean(String url, int port) {
        urlProperty.set(url);
        portProperty.set(port);
    }

    @Override
    public String getUrl() {
        return urlProperty.get();
    }

    @Override
    public int getPort() {
        return portProperty.get();
    }


    public String getUrlProperty() {
        return urlProperty.get();
    }

    public ObservableStringValue urlPropertyProperty() {
        return urlProperty;
    }

    public void setUrlProperty(String urlProperty) {
        this.urlProperty.setValue(urlProperty);
    }

    public Number getPortProperty() {
        return portProperty.get();
    }

    public ObservableIntegerValue portPropertyProperty() {
        return portProperty;
    }

    public void setPortProperty(int portProperty) {
        this.portProperty.set(portProperty);
    }

    public ObservableSet<Account> getAccounts() {
        return accounts;
    }

    public boolean addAccount(Account account) {
        return accounts.add(account);
    }

    public boolean removeAccount(Account account) {
        return accounts.remove(account);
    }

    @Override
    public int hashCode(){
        return urlProperty.get().hashCode() + portProperty.get();
    }

    @Override
    public boolean equals(Object obj) {
        if (this ==obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cloud cloud = (Cloud) obj;
        return this.hashCode() == obj.hashCode()? true: false;

    }

    @Override
    public String toString() {
       StringBuilder stringBuilder = new StringBuilder();
        for (Account account: accounts
             ) {
            stringBuilder.append(account.toString()).append("/n");
        }                
        return "[" + getClass().getName() + "/n" +
                "url: " + urlProperty.get() + "/n" +
                "port: " + portProperty.get() + "/n" +
                Account.class.getName() + ":/n" +
                stringBuilder.toString() +"]";
    }

}
