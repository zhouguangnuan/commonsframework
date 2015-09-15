/**<p>项目名：</p>
 * <p>包名：	cn.singno.commonsframework.validation.constraints</p>
 * <p>文件名：CrossParameter.java</p>
 * <p>版本信息：</p>
 * <p>日期：2014年8月16日-下午4:08:47</p>
 * Copyright (c) 2014singno公司-版权所有
 */
package cn.singno.commonsframework.validation.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import cn.singno.commonsframework.validation.constraintvalidators.CrossParameterValidator;

/**<p>名称：CrossParameter.java</p>
 * <p>描述：</p>
 * <pre>
 *         
 * </pre>
 * @author 周光暖
 * @date 2014年8月16日 下午4:08:47
 * @version 1.0.0
 */
@Constraint(validatedBy = CrossParameterValidator.class)
@Target({ METHOD, CONSTRUCTOR, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Documented
public @interface CrossParameter {

    String message() default "{password.confirmation.error}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

}
