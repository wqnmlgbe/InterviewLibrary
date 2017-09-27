package open.cklan.com.interviewlibrary.category.day12_mvp.scope;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Scope;

/**
 * AUTHOR：lanchuanke on 17/9/27 10:24
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
