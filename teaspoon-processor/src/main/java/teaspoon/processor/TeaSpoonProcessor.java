package teaspoon.processor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import teaspoon.TeaSpoon;
import teaspoon.annotations.OnUi;

@Aspect
public class TeaSpoonProcessor {
    private static final String TAG = TeaSpoonProcessor.class.getSimpleName();

    @Pointcut("execution(@teaspoon.annotations.OnBackground * *(..))")
    public void methodWithOnBackgroundAnnotation() { }

    @Pointcut("execution(@teaspoon.annotations.OnUi * *(..))")
    public void methodWithOnUiAnnotation() { }

    @Around("methodWithOnBackgroundAnnotation()")
    public void executeOnBackground(final ProceedingJoinPoint joinPoint) throws Exception{
        TeaSpoon.getInstance().onBackground(new Runnable() {
            @Override public void run() {
                try {
                    joinPoint.proceed();
                } catch (Throwable throwable) {
                    throw new IllegalStateException(throwable);
                }
            }
        });
    }

    @Around("methodWithOnUiAnnotation()")
    public void methodWithOnUiAnnotation(final ProceedingJoinPoint joinPoint) throws Throwable {
        Runnable runnableJoinPoint = new Runnable() {
            @Override public void run() {
                try {
                    joinPoint.proceed();
                } catch (Throwable throwable) {
                    throw new IllegalStateException(throwable);
                }
            }
        };

        MethodSignature targetMethodSignature = (MethodSignature) joinPoint.getSignature();
        OnUi annotation = targetMethodSignature.getMethod().getAnnotation(OnUi.class);

        TeaSpoon.getInstance().onUi(runnableJoinPoint, annotation.delay());
    }
}
