package io.github.sibir007.clouds5.core.transactions;

public class AddAccountTransaction extends AbstractSimpleTransaction {
    private final String login;
    private final String email;
    private final String password;
    private final String repeatPassword;

    public AddAccountTransaction(String id,
                                 String rootId,
                                 String parentId,
                                 Direction direction,
                                 Status status,
                                 String host,
                                 int port,
                                 String responseId, String login, String email, String password, String repeatPassword) {
        super(id,
                rootId,
                parentId,
                direction,
                status,
                TransactionType.ADD_ACCOUNT,
                TransactionCategory.CLIENT,
                host,
                port,
                AbstractSimpleTransaction.NOUSED_NAME,
                AbstractSimpleTransaction.NOUSED_PASSWORD,
                responseId);
        this.login = login;
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }
}
