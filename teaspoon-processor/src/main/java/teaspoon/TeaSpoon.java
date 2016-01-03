package teaspoon;

/**
 * Created by minkyu on 2015. 12. 29..
 */
public class TeaSpoon {
    private static final String EXCEPTION_NOT_INITIALIZED = "TeaSpoon is not initialized.";
    private static final String EXCEPTION_ALREADY_INITIALIZED = "You cannot initialize TeaSpoon twice.";

    private TeaSpoon() {}

    private static ThreadManager threadManager;

    public static void initialize() {
        threadManager = ThreadManager.getInstance();
    }

    public static void onUi(Runnable runnable) {
        threadManager.runOnUiThread(runnable);
    }

    public static void onUi(Runnable runnable, int delay) {
        threadManager.runOnUiThread(runnable, delay);
    }

    public static void onBackground(Runnable runnable) {
        threadManager.runOnBackgroundThread(runnable);
    }
}
