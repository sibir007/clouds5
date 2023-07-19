package io.github.sibir007.clouds5.client.gui.fx.model;

import io.github.sibir007.clouds5.client.core.Account;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.util.Objects;

public class AccountBeenImpl implements Account {
    private SimpleStringProperty userName = new SimpleStringProperty(this, "userName");
    private SimpleStringProperty password = new SimpleStringProperty(this, "password");

    public AccountBeenImpl(String userName, String password) {
        this.userName.set(userName);
        this.password.set(password);
    }

    @Override
    public String getUserName() {
        return userName.get();
    }

    @Override
    public String getPassword() {
        return password.get();
    }

    @Override
    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    @Override
    public void setPassword(String password) {
        this.password.set(password);
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
