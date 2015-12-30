package teaspoon.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS) @Target(ElementType.METHOD)
public @interface OnMain {
    int delay() default 0;
}
