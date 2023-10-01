package io.github.sibir007.clouds5.client.gui.fx.model;

import io.github.sibir007.clouds5.core.Account;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class AccountBeenImpl implements Account {
    private static final String nameUserNameProperty  = "userName";
    public static String getNameUserNameProperty(){
        return nameUserNameProperty;
    }
    private static final String namePasswordProperty  = "password";
    public static String getNamePasswordProperty(){
        return namePasswordProperty;
    }
    private SimpleStringProperty userName = new SimpleStringProperty(this, nameUserNameProperty);

    private SimpleStringProperty password = new SimpleStringProperty(this, namePasswordProperty);

    public AccountBeenImpl(String userName, String password) {
        this.userName.set(userName);
        this.password.set(password);
    }

    @Override
    public String getUserName() {
        return userName.get();
    }
    @Override
    public void setUserName(String userName) {
        this.userName.set(userName);
    }
    public SimpleStringProperty getUserNameProperty(){
        return userName;
    }

    @Override
    public String getPassword() {
        return password.get();
    }

    @Override
    public void setPassword(String password) {
        this.password.set(password);
    }
    public SimpleStringProperty getPasswordProperty(){
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountBeenImpl account = (AccountBeenImpl) o;
        return Objects.equals(userName.get(), account.userName.get());
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName.get());
    }

    @Override
    public String toString() {
        return "AccountBeenImpl{" +
                "userName='" + userName.get() + '\'' +
                ", password='" + password.get() + '\'' +
                '}';
    }
}
