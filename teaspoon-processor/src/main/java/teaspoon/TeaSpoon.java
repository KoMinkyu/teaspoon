package teaspoon;

/**
 * Singleton class for execute task in Ui thread or Background thread.
 * This class must be initialized with {@link #initialize()} method before using other functions.
 */
public class TeaSpoon {
    private static final String TAG = TeaSpoon.class.getSimpleName();

    private static final String ERROR_NOT_INITIALIZED = "TeaSpoon is not initialized.";
    private static final String WARNING_ALREADY_INITIALIZED = "TeaSpoon is already initialized before.";

    private static ExecuteEngineFactory executeEngineFactory;

    private volatile static TeaSpoon instance;

    /** Returns singleton class instance. */
    public static TeaSpoon getInstance() {
        if (instance == null) {
            synchronized (TeaSpoon.class) {
                if (instance ==null) {
                    instance = new TeaSpoon();
                }
            }
        }

        return instance;
    }

    protected TeaSpoon() {}

    /**
     * Initialize TeaSpoon instance.
     */
    public static void initialize() {
        executeEngineFactory = ExecuteEngineFactory.getInstance();
    }

    /**
     * Run runnable on Ui thread.
     * {@link #initialize()} method must be called before call this method.
     *
     * @param runnable Runnable instance that will be executed on Ui thread.
     * @throws IllegalStateException if {@link #initialize()} method wasn't called before
     * @throws IllegalArgumentException if passed runnable argument is null
     */
    public synchronized void onUi(Runnable runnable) {
        onUi(runnable, 0);
    }

    /**
     * Run runnable on Ui thread.
     * {@link #initialize()} method must be called before call this method.
     *
     * @param runnable Runnable instance that will be executed on Ui thread.
     * @param delay is amount of time that runnable will be elapsed.
     * @throws IllegalStateException if {@link #initialize()} method wasn't called before
     * @throws IllegalArgumentException if passed runnable or delay argument is null
     */
    public synchronized void onUi(Runnable runnable, int delay) {
        checkInitialized();
        if (runnable == null || delay < 0 ) {
            StringBuilder builder = new StringBuilder("Wrong arguments were passed to onUi")
                    .append(" method with:");
            if (runnable == null) {
                builder.append("\nNull runnable");
            }

            if (delay < 0) {
                builder.append("\nOut ranged delay");
            }

            throw new IllegalArgumentException(builder.toString());
        }

        executeEngineFactory.runOnUiThread(runnable, delay);
    }

    /**
     * Run runnable on Background thread.
     * {@link #initialize()} method must be called before call this method.
     *
     * @param runnable Runnable instance that will be executed on Background thread.
     * @throws IllegalStateException if {@link #initialize()} method wasn't called before
     * @throws IllegalArgumentException if passed runnable or delay argument is null
     */
    public synchronized void onBackground(Runnable runnable) {
        checkInitialized();
        if (runnable == null) {
            StringBuilder builder = new StringBuilder("Wrong arguments were passed to onBackground")
                    .append(" method with:")
                    .append("\nNull runnable");

            throw new IllegalArgumentException(builder.toString());
        }

        executeEngineFactory.runOnBackgroundThread(runnable);
    }

    /**
     * Checks if TeaSpoon class instance was initialized
     *
     * @throws IllegalStateException if instance wasn't initialized
     */
    private void checkInitialized() {
        if (executeEngineFactory == null) {
            throw new IllegalStateException(ERROR_NOT_INITIALIZED);
        }
    }
}
