package teaspoon;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

final class ExecuteEngine {
  private final ExecutorService threadExecutor;
  private final Handler uiExecutor;

  ExecuteEngine() {
    this(Executors.newCachedThreadPool());
  }

  ExecuteEngine(ExecutorService customExecutorService) {
    threadExecutor = customExecutorService;
    uiExecutor = new Handler(Looper.getMainLooper());
  }

  void runOnUiThread(Runnable runnable, int delay) {
    uiExecutor.postDelayed(runnable, delay);
  }

  void runOnBackgroundThread(Runnable runnable) {
    threadExecutor.submit(runnable);
  }
}
