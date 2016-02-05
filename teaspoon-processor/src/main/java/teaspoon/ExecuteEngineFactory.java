package teaspoon;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuteEngineFactory {
    private static ExecuteEngineFactory instance;

    public static ExecuteEngineFactory getInstance() {
        if (instance == null) {
            synchronized (ExecuteEngineFactory.class) {
                if (instance == null) {
                    instance = new ExecuteEngineFactory();
                }
            }
        }

        return instance;
    }

    private final ExecutorService threadExecutor;
    private final Handler uiExecutor;

    ExecuteEngineFactory() {
        threadExecutor = Executors.newCachedThreadPool();
        uiExecutor = new Handler(Looper.getMainLooper());
    }

    public void runOnUiThread(Runnable runnable, int delay) {
        uiExecutor.postDelayed(runnable, delay);
    }

    public void runOnBackgroundThread(Runnable runnable) {
        threadExecutor.submit(runnable);
    }
}
