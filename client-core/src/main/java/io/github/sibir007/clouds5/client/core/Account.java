package io.github.sibir007.clouds5.client.core;

public interface Account {
    Cloud getCloud();
    String getUserName();
    String getPassword();
    String getEmail();
}
