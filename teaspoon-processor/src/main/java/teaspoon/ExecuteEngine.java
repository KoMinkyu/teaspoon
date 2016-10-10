package teaspoon;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuteEngine {
    private final ExecutorService threadExecutor;
    private final Handler uiExecutor;

    public ExecuteEngine() {
        this(Executors.newCachedThreadPool());
    }

    public ExecuteEngine(ExecutorService customExecutorService) {
        threadExecutor = customExecutorService;
        uiExecutor = new Handler(Looper.getMainLooper());
    }

    public void runOnUiThread(Runnable runnable, int delay) {
        uiExecutor.postDelayed(runnable, delay);
    }

    public void runOnBackgroundThread(Runnable runnable) {
        threadExecutor.submit(runnable);
    }
}
