package muddassir.com.expensemanager;

import java.io.Serializable;
import java.util.List;

/**
 * @author muddassir on 3/10/18.
 */

public class Transaction implements Serializable{
    private int id;
    private String transactionDetails;
    private double transactionAmount;
    private List<User> participants;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(String transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }
}
