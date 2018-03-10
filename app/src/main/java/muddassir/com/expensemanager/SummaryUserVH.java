package muddassir.com.expensemanager;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

/**
 * @author muddassir on 3/10/18.
 */

public class SummaryUserVH extends BaseVH {
    private TextView userName, amount;

    public SummaryUserVH(View itemView) {
        super(itemView);
        userName = itemView.findViewById(R.id.userName);
        amount = itemView.findViewById(R.id.amount);
    }

    public void fillUserSummary(User user, ExpenseAdapter.OnItemClickListener onItemClickListener) {
        userName.setText(user.getName());
        double amountRemaining = user.getPaidAmount() - user.getShareAmount();
        amount.setText(String.valueOf(amountRemaining));

        if (amountRemaining < 0) {
            amount.setTextColor(Color.RED);
        } else {
            amount.setTextColor(Color.GREEN);
        }
    }
}
