package muddassir.com.expensemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity implements ExpenseAdapter.OnItemClickListener {
    public static final String EXTRA_CONTACT = "extra_contact";
    EditText search;
    List<TypedItem> contacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        search = findViewById(R.id.search);
        RecyclerView contactList = findViewById(R.id.contactList);
        contactList.setLayoutManager(new LinearLayoutManager(this));

        fetchContacts();
        ExpenseAdapter adapter = new ExpenseAdapter(this, contacts);
        adapter.setOnItemClickListener(this);
        contactList.setAdapter(adapter);
    }

    private void fetchContacts() {
        List<User> users = ExpenseHelper.getDummyUsers();
        for (User user : users) {
            contacts.add(new TypedItem(ExpenseHelper.TYPE_CONTACT_USER, user));
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_CONTACT, (User) contacts.get(position).data);
        setResult(RESULT_OK, intent);
        finish();
    }
}
