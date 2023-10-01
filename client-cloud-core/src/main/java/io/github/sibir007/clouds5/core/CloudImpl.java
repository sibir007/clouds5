package io.github.sibir007.clouds5.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CloudImpl implements Cloud, Serializable {
    private static final long serialVersionUID = 1L;
    private String host;
    private int port;

    private CloudAvailabilityStatus cloudAvailabilityStatus = CloudAvailabilityStatus.FILED;
    private ArrayList<Account> accounts = new ArrayList<>();

    public CloudImpl(String host, int port) {
        this.host = host;
        this.port = port;
    }


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }


    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public boolean addAccount(Account account) {
        assert account instanceof AccountImpl;
        return accounts.add(account);
    }

    @Override
    public boolean removeAccount(Account account) {
        return accounts.remove(account);
    }

    @Override
    public boolean setAccounts(List<Account> accounts) {
        assert accounts instanceof ArrayList;
        this.accounts = (ArrayList<Account>) accounts;
        return false;
    }


    public List<Account> getAccounts() {
        return accounts;
    }

    @Override
    public void setCloudAvailabilityStatus(CloudAvailabilityStatus status) {
        this.cloudAvailabilityStatus = cloudAvailabilityStatus;
    }

    @Override
    public CloudAvailabilityStatus getCloudAvailabilityStatus() {
        return cloudAvailabilityStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CloudImpl cloud = (CloudImpl) o;
        return Objects.equals(host, cloud.getHost()) & port == cloud.getPort();
    }

    @Override
    public int hashCode() {
        return Objects.hash(host) + port;
    }

    @Override
    public String toString() {
        return "CloudImpl{" +
                "host='" + host + '\'' +
                ", port='" + port + '\'' +
                "accounts=" + accounts.toString() +
                '}';
    }


}
