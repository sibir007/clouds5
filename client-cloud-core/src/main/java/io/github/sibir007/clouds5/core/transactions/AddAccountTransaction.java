package io.github.sibir007.clouds5.core.transactions;

public interface AddAccountTransaction extends SimpleTransaction{
    String getLogin();
    String getEmail();
    String getPassword();
    String getRepeatPassword();
}
