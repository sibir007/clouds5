package io.github.sibir007.clouds5.core;


import java.util.List;

public interface Cloud {
    String getHost();

    void setHost(String host);

    int getPort();

    void setPort(int port);

    boolean addAccount(Account account);

    boolean removeAccount(Account account);

    boolean setAccounts(List<Account> accounts);

    List<Account> getAccounts();

    void setCloudAvailabilityStatus(CloudAvailabilityStatus status);
    CloudAvailabilityStatus getCloudAvailabilityStatus();

    public enum CloudAvailabilityStatus {
        OK,
        REJECTED,
        NO_CONNECTION,
        FILED,
        EXCEPTION
    }

}
