package teaspoon;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

final class ExecuteEngine {
    private static ExecuteEngine instance;

    public static ExecuteEngine getInstance() {
        if (instance == null) {
            synchronized (ExecuteEngine.class) {
                if (instance == null) {
                    instance = new ExecuteEngine();
                }
            }
        }

        return instance;
    }

    private final ExecutorService threadExecutor;
    private final Handler uiExecutor;

    ExecuteEngine() {
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
