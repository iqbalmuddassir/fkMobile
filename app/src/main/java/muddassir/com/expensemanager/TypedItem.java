package muddassir.com.expensemanager;

/**
 * @author muddassir on 3/10/18.
 */

public class TypedItem {
    int type;
    Object data;

    public TypedItem(int type, Object data) {
        this.type = type;
        this.data = data;
    }
}
