/**<p>项目名：</p>
 * <p>包名：	cn.singno.commonsframework.validation.constraints</p>
 * <p>文件名：PropertyScriptAssert.java</p>
 * <p>版本信息：</p>
 * <p>日期：2014年8月16日-下午3:06:16</p>
 * Copyright (c) 2014singno公司-版权所有
 */
package cn.singno.commonsframework.validation.constraints;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import cn.singno.commonsframework.validation.constraintvalidators.PropertyScriptAssertValidator;

/**<p>名称：PropertyScriptAssert.java</p>
 * <p>描述：</p>
 * <pre>
 *         
 * </pre>
 * @author 周光暖
 * @date 2014年8月16日 下午3:06:16
 * @version 1.0.0
 */
@Target({ TYPE })  
@Retention(RUNTIME)  
@Constraint(validatedBy = {PropertyScriptAssertValidator.class})  
@Documented  
public @interface PropertyScriptAssert {  
  
    String message() default "{org.hibernate.validator.constraints.ScriptAssert.message}";  
  
    Class<?>[] groups() default { };  
  
    Class<? extends Payload>[] payload() default { };  
  
    String lang();  
  
    String script();  
  
    String alias() default "_this";  
  
    String property();  
  
    @Target({ TYPE })  
    @Retention(RUNTIME)  
    @Documented  
    public @interface List {  
        PropertyScriptAssert[] value();  
    }  
}  