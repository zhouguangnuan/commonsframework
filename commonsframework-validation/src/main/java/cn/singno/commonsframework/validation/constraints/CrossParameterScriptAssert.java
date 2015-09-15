package cn.singno.commonsframework.validation.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintTarget;
import javax.validation.Payload;

import cn.singno.commonsframework.validation.constraintvalidators.CrossParameterScriptAssertValidator;

/**
 * <p>名称：CrossParameterScriptAssert.java</p>
 * <p>描述：</p>
 * <pre>
 *         
 * </pre>
 * @author 周光暖
 * @date 2014-11-11 下午9:14:24
 * @version 1.0.0
 */
@Constraint(validatedBy = {
		CrossParameterScriptAssertValidator.class,
})
@Target({ TYPE, FIELD, PARAMETER, METHOD, CONSTRUCTOR, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Documented
public @interface CrossParameterScriptAssert {
    String message() default "error";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    String script();
    String lang();
    String alias() default "_this";
    String property() default "";
    ConstraintTarget validationAppliesTo() default ConstraintTarget.IMPLICIT;
}
