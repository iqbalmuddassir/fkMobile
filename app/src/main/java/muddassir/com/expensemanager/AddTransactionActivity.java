package muddassir.com.expensemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AddTransactionActivity extends AppCompatActivity implements View.OnClickListener, ExpenseAdapter.OnItemClickListener {
    public static final String EXTRA_TRANSACTION = "extra_transaction";
    private static final int REQUEST_CODE_CONTACT = 121;
    private EditText transactionDetails, transactionAmount;
    private RecyclerView usersList;
    private Button addParticipants, addTransaction;
    private ExpenseAdapter expenseAdapter;
    private List<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        transactionDetails = findViewById(R.id.transactionDetails);
        transactionAmount = findViewById(R.id.transactionAmount);
        usersList = findViewById(R.id.usersList);
        addParticipants = findViewById(R.id.addParticipants);
        addTransaction = findViewById(R.id.addTransaction);

        usersList.setLayoutManager(new LinearLayoutManager(this));
        expenseAdapter = new ExpenseAdapter(this, new ArrayList<TypedItem>());
        expenseAdapter.setOnItemClickListener(this);
        usersList.setAdapter(expenseAdapter);

        addTransaction.setOnClickListener(this);
        addParticipants.setOnClickListener(this);
    }

    private void validateAndAddTransaction() {
        List<User> users = new ArrayList<>();
        double sum = getUsers(users);
        double totalAmount = Double.parseDouble(transactionAmount.getText().toString());
        if (users.size() == 0) {
            Toast.makeText(this, "Please add participants", Toast.LENGTH_SHORT).show();
            return;
        } else if (sum > totalAmount) {
            Toast.makeText(this, "Expense paid cannot be greater than total amount", Toast.LENGTH_SHORT).show();
            return;
        } else if (sum < totalAmount) {
            Toast.makeText(this, "Total sum cannot be less that paid amounts", Toast.LENGTH_SHORT).show();
            return;
        }

        Transaction transaction = new Transaction();
        transaction.setParticipants(users);
        transaction.setTransactionAmount(totalAmount);
        transaction.setTransactionDetails(transactionDetails.getText().toString());

        Intent intent = new Intent();
        intent.putExtra(EXTRA_TRANSACTION, transaction);
        setResult(RESULT_OK, intent);
        finish();
    }

    private double getUsers(List<User> users) {
        double sum = 0;
        List<TypedItem> itemList = expenseAdapter.getDataList();
        for (TypedItem typedItem : itemList) {
            if (typedItem.data instanceof User) {
                User user = (User) typedItem.data;
                users.add(user);
                sum += user.getPaidAmount();
            }
        }
        return sum;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addParticipants:
                openContact(users);
                break;
            case R.id.addTransaction:
                validateAndAddTransaction();
                break;
        }
    }

    private void openContact(List<User> users) {
        Intent intent = new Intent(this, ContactActivity.class);
        startActivityForResult(intent, REQUEST_CODE_CONTACT);
    }

    @Override
    public void onItemClick(int position) {
        expenseAdapter.remove(position);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CONTACT && resultCode == RESULT_OK) {
            addParticipant(data.getSerializableExtra(ContactActivity.EXTRA_CONTACT));
        }
    }

    private void addParticipant(Serializable serializableExtra) {
        if (serializableExtra instanceof User) {
            User user = (User) serializableExtra;
            if (!users.contains(user)) {
                users.add(user);
                expenseAdapter.add(new TypedItem(ExpenseHelper.TYPE_EXPENSE_USER, user));
            }
        }
    }
}
