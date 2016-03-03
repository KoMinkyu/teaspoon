package teaspoon.processor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.HashMap;

import teaspoon.TeaSpoon;
import teaspoon.annotations.OnUi;

@Aspect
public class TeaSpoonProcessor {
    private static final String TAG = TeaSpoonProcessor.class.getSimpleName();

    private HashMap<Integer, Integer> processedMap = new HashMap<>();

    @Pointcut("execution(@teaspoon.annotations.OnBackground * *(..))")
    public void methodWithOnBackgroundAnnotation() { }

    @Pointcut("execution(@teaspoon.annotations.OnUi * *(..))")
    public void methodWithOnUiAnnotation() { }

    @Around("methodWithOnBackgroundAnnotation()")
    public void executeOnBackground(final ProceedingJoinPoint joinPoint) {
        TeaSpoon.getInstance().onBackground(new Runnable() {
            @Override
            public void run() {
                try {
                    joinPoint.proceed();
                } catch (Throwable throwable) {
                    throw new IllegalStateException(throwable);
                }
            }
        });
    }

    @Around("methodWithOnUiAnnotation()")
    public void methodWithOnUiAnnotation(final ProceedingJoinPoint joinPoint) {
        Runnable runnableJoinPoint = new Runnable() {
            @Override public void run() {
                try {
                    joinPoint.proceed();
                } catch (Throwable throwable) {
                    throw new IllegalStateException(throwable);
                }
            }
        };

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        int methodHashCode = method.hashCode();

        int delay;

        if (processedMap.containsKey(methodHashCode)) {
            delay = processedMap.get(methodHashCode);
        } else {
            delay = processMethodAndGetDelay(method);
            processedMap.put(methodHashCode, delay);
        }

        TeaSpoon.getInstance().onUi(runnableJoinPoint, delay);
    }

    private int processMethodAndGetDelay(final Method method) {
        if (method == null) {
            throw new IllegalStateException("Method cannot be null");
        }

        OnUi onUiAnnotation = method.getAnnotation(OnUi.class);
        if (onUiAnnotation == null) {
            throw new IllegalStateException("Can't find @OnUi annotation from method");
        }

        return onUiAnnotation.delay();
    }
}
