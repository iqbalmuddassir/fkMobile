package muddassir.com.expensemanager;

import android.support.annotation.IntDef;

import java.util.ArrayList;
import java.util.List;

/**
 * @author muddassir on 3/10/18.
 */

public class ExpenseHelper {
    public static final int TYPE_SUMMARY_USER = 0;
    public static final int TYPE_EXPENSE_USER = 1;
    public static final int TYPE_CONTACT_USER = 2;
    private static List<User> users = new ArrayList<>();
    static {
        users.add(new User(users.size(), "Nikhil"));
        users.add(new User(users.size(), "Neeraj"));
        users.add(new User(users.size(), "Deepak"));
        users.add(new User(users.size(), "Shivam"));
        users.add(new User(users.size(), "Muddassir"));
        users.add(new User(users.size(), "Anil"));
    }

    public static List<User> getDummyUsers() {
        return users;
    }

    @IntDef({
            TYPE_SUMMARY_USER,
            TYPE_EXPENSE_USER,
            TYPE_CONTACT_USER
    })
    public @interface ViewTypes {
    }
}
