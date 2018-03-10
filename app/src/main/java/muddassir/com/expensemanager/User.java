package muddassir.com.expensemanager;

import java.io.Serializable;

/**
 * @author muddassir on 3/10/18.
 */

public class User implements Serializable {
    private int id;
    private String name;
    private double paidAmount;
    private double shareAmount;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public double getShareAmount() {
        return shareAmount;
    }

    public void setShareAmount(double shareAmount) {
        this.shareAmount = shareAmount;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof User && ((User) obj).id == this.id;
    }
}
