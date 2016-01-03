package teaspoon.processor;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class TeaSpoonProcessor {
    private static final String TAG = TeaSpoonProcessor.class.getSimpleName();

    @Pointcut("execution(@teaspoon.annotations.OnBackground * *(..))")
    public void methodWithOnBackgroundAnnotation() { }

    @Pointcut("execution(@teaspoon.annotations.OnUi * *(..))")
    public void methodWithOnUiAnnotation() { }

    @Around("methodWithOnBackgroundAnnotation()")
    public Object executeOnBackground(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.i("TAG", "background method!!");
        Object result = joinPoint.proceed();

        return result;
    }

    @Around("methodWithOnUiAnnotation()")
    public Object methodWithOnUiAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.i("TAG", "background method!!");
        Object result = joinPoint.proceed();

        return result;
    }
}
