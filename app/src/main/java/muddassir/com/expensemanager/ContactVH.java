package muddassir.com.expensemanager;

import android.view.View;
import android.widget.TextView;

/**
 * @author muddassir on 3/10/18.
 */

class ContactVH extends BaseVH {
    private TextView userName;

    public ContactVH(View view) {
        super(view);
        userName = view.findViewById(R.id.userName);
    }

    public void fillUserInformation(User user, final ExpenseAdapter.OnItemClickListener listener) {
        userName.setText(user.getName());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(getAdapterPosition());
            }
        });
    }
}
