package teaspoon.processor;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import teaspoon.TeaSpoon;

@Aspect
public class TeaSpoonProcessor {
    private static final String TAG = TeaSpoonProcessor.class.getSimpleName();

    @Pointcut("execution(@teaspoon.annotations.OnBackground * *(..))")
    public void methodWithOnBackgroundAnnotation() { }

    @Pointcut("execution(@teaspoon.annotations.OnUi * *(..))")
    public void methodWithOnUiAnnotation() { }

    @Around("methodWithOnBackgroundAnnotation()")
    public void executeOnBackground(final ProceedingJoinPoint joinPoint) throws Throwable {
        Log.i(TAG, joinPoint.getSignature().getName() + "execute on background thread.");
        TeaSpoon.onBackground(new Runnable() {
            @Override
            public void run() {
                try {
                    joinPoint.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
    }

    @Around("methodWithOnUiAnnotation()")
    public void methodWithOnUiAnnotation(final ProceedingJoinPoint joinPoint) throws Throwable {
        Log.i(TAG, joinPoint.getSignature().getName() + "execute on ui thread.");
        TeaSpoon.onUi(new Runnable() {
            @Override
            public void run() {
                try {
                    joinPoint.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
    }
}
