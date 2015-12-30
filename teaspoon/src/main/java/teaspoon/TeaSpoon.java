package teaspoon;

import android.content.Context;

/**
 * Created by minkyu on 2015. 12. 29..
 */
public class TeaSpoon {
    private static final String EXCEPTION_NOT_INITIALIZED = "TeaSpoon is not initialized.";
    private static final String EXCEPTION_ALREADY_INITIALIZED = "You cannot initialize TeaSpoon twice.";

    private TeaSpoon() {}

    public static void initialize(Context context) {
    }
}
