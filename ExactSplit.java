package CostSharingApp.com.costSharing.models;

import CostSharingApp.com.costSharing.User;

public class ExactSplit extends Split {

    public ExactSplit(User user, double amount) {
        super(user);
        this.amount = amount;
    }
}