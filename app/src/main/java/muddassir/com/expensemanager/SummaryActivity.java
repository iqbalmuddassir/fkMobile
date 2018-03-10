package muddassir.com.expensemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SummaryActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADD = 123;
    private ExpenseAdapter expenseAdapter;
    private List<User> users = new ArrayList<>();
    private List<TypedItem> summaryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView expenseList = findViewById(R.id.expenseList);
        expenseList.setLayoutManager(new LinearLayoutManager(this));
        expenseAdapter = new ExpenseAdapter(this, summaryList);
        expenseList.setAdapter(expenseAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddNewTransaction();
            }
        });
    }

    private void openAddNewTransaction() {
        Intent intent = new Intent(this, AddTransactionActivity.class);
        startActivityForResult(intent, REQUEST_CODE_ADD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD && resultCode == RESULT_OK) {
            addTransaction(data.getSerializableExtra(AddTransactionActivity.EXTRA_TRANSACTION));
        }
    }

    private void addTransaction(Serializable dataSerializableExtra) {
        if (dataSerializableExtra instanceof Transaction) {
            Transaction transaction = (Transaction) dataSerializableExtra;
            List<User> participants = transaction.getParticipants();
            for (User participant : participants) {
                if (!users.contains(participant)) {
                    users.add(participant);
                } else {
                    int ix = users.indexOf(participant);
                    if (ix >= 0) {
                        User user = users.get(ix);
                        user.setPaidAmount(user.getPaidAmount() + participant.getPaidAmount());
                        user.setShareAmount(user.getShareAmount() + participant.getShareAmount());
                    }
                }
            }
            updateData();
            expenseAdapter.notifyDataSetChanged();
        }
    }

    private void updateData() {
        summaryList.clear();
        for (User user : users) {
            summaryList.add(new TypedItem(ExpenseHelper.TYPE_SUMMARY_USER, user));
        }
    }
}
