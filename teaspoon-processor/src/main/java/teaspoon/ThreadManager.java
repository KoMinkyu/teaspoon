package teaspoon;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

final class ThreadManager {
    private static teaspoon.ThreadManager instance;

    public static teaspoon.ThreadManager getInstance() {
        if (instance == null) {
            synchronized (teaspoon.ThreadManager.class) {
                if (instance == null) {
                    instance = new teaspoon.ThreadManager();
                }
            }
        }

        return instance;
    }

    private final ExecutorService threadExecutor;
    private final Handler uiExecutor;

    ThreadManager() {
        threadExecutor = Executors.newCachedThreadPool();
        uiExecutor = new Handler(Looper.getMainLooper());
    }

    public void runOnUiThread(Runnable runnable) {
        runOnUiThread(runnable, 0);
    }

    public void runOnUiThread(Runnable runnable, int delay) {
        uiExecutor.postDelayed(runnable, delay);
    }

    public void runOnBackgroundThread(Runnable runnable) {
        threadExecutor.submit(runnable);
    }
}
