package teaspoon.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by makeus on 2015. 12. 30..
 */
@Retention(RetentionPolicy.CLASS) @Target(ElementType.METHOD)
public @interface OnBackground {
}
