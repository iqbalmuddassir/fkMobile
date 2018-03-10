package muddassir.com.expensemanager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author muddassir on 3/10/18.
 */

public class TransactionUserVH extends BaseVH {
    private TextView userName;
    private EditText paidAmount, shareAmount;
    private View delete;
    private User mUser;

    public TransactionUserVH(View itemView) {
        super(itemView);
        userName = itemView.findViewById(R.id.userName);
        paidAmount = itemView.findViewById(R.id.paidAmount);
        shareAmount = itemView.findViewById(R.id.shareAmount);
        delete = itemView.findViewById(R.id.delete);
    }

    public void fillUserSummary(final User user, final ExpenseAdapter.OnItemClickListener listener) {
        this.mUser = user;
        userName.setText(user.getName());
        paidAmount.setText(String.valueOf(user.getPaidAmount()));
        shareAmount.setText(String.valueOf(user.getShareAmount()));

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(getAdapterPosition());
            }
        });

        paidAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    mUser.setPaidAmount(Double.parseDouble(s.toString()));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });

        shareAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    mUser.setShareAmount(Double.parseDouble(s.toString()));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
