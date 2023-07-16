package io.github.sibir007.clouds5.client.gui.fx.model.bean;
import io.github.sibir007.clouds5.client.core.Account;
import javafx.beans.property.SimpleStringProperty;

public class AccountBean implements Account {
    private final SimpleStringProperty userNameProperty = new SimpleStringProperty();
    private final SimpleStringProperty passwordProperty = new SimpleStringProperty();

    @Override
    public String getUserName() {
        return getUserNameProperty();
    }

    @Override
    public String getPassword() {
        return getPasswordProperty();
    }

    public void setUserNameProperty(String userName) {
        userNameProperty.set(userName);
    }

    public String getUserNameProperty(){
        return userNameProperty.get();
    }

    public SimpleStringProperty userNamePropertyProperty() {
        return userNameProperty;
    }

    public String getPasswordProperty() {
        return passwordProperty.get();
    }
    public void setPasswordProperty(String password) {
        passwordProperty.set(password);
    }

    public SimpleStringProperty passwordPropertyProperty() {
        return passwordProperty;
    }

    @Override
    public int hashCode(){
        return userNameProperty.get().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this ==obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Account cloud = (Account) obj;
        return this.hashCode() == obj.hashCode()? true: false;

    }

    @Override
    public String toString() {
        return "[Account:" + userNameProperty.get() + "]";
    }


}




