package io.github.sibir007.clouds5.core.transactions;

public class AddAccountTransactionImpl extends AbstractSimpleTransaction implements AddAccountTransaction {
    private final String login;
    private final String email;
    private final String password;
    private final String repeatPassword;

    public AddAccountTransactionImpl(String id,
                                     String rootId,
                                     String parentId,
                                     Direction direction,
                                     Status status,
                                     String host,
                                     int port,
                                     String responseId,
                                     String login,
                                     String email,
                                     String password,
                                     String repeatPassword) {
        super(id,
                rootId,
                parentId,
                direction,
                status,
                TransactionType.ADD_ACCOUNT,
                TransactionCategory.CLIENT,
                responseId,
                host,
                port);
        this.login = login;
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getRepeatPassword() {
        return repeatPassword;
    }
}
