package muddassir.com.expensemanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author muddassir on 3/10/18.
 */

public class ExpenseAdapter extends RecyclerView.Adapter<BaseVH> {
    private Context context;
    private List<TypedItem> dataList;
    private OnItemClickListener onItemClickListener;

    public ExpenseAdapter(Context context, List<TypedItem> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public BaseVH onCreateViewHolder(ViewGroup parent, @ExpenseHelper.ViewTypes int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = null;
        BaseVH vh = null;
        switch (viewType) {
            case ExpenseHelper.TYPE_SUMMARY_USER:
                view = inflater.inflate(R.layout.summary_user_item, parent, false);
                vh = new SummaryUserVH(view);
                break;
            case ExpenseHelper.TYPE_EXPENSE_USER:
                view = inflater.inflate(R.layout.transaction_user_item, parent, false);
                vh = new TransactionUserVH(view);
                break;
            case ExpenseHelper.TYPE_CONTACT_USER:
                view = inflater.inflate(R.layout.contact_user_item, parent, false);
                vh = new ContactVH(view);
                break;
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(BaseVH holder, int position) {
        if (holder instanceof SummaryUserVH) {
            ((SummaryUserVH) holder).fillUserSummary((User) dataList.get(position).data, onItemClickListener);
        } else if (holder instanceof TransactionUserVH) {
            ((TransactionUserVH) holder).fillUserSummary((User) dataList.get(position).data, onItemClickListener);
        } else if (holder instanceof ContactVH) {
            ((ContactVH) holder).fillUserInformation((User) dataList.get(position).data, onItemClickListener);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return dataList.get(position).type;
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    public List<TypedItem> getDataList() {
        return dataList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void remove(int position) {
        dataList.remove(position);
        notifyItemRemoved(position);
    }

    public void add(TypedItem typedItem) {
        dataList.add(typedItem);
        notifyItemInserted(dataList.size());
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
