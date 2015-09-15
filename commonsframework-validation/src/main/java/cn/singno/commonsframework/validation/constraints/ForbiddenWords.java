/**<p>项目名：</p>
 * <p>包名：	cn.singno.commonsframework.validation.constraints</p>
 * <p>文件名：ForbiddenWords.java</p>
 * <p>版本信息：</p>
 * <p>日期：2014年8月13日-下午10:29:34</p>
 * Copyright (c) 2014singno公司-版权所有
 */
package cn.singno.commonsframework.validation.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import cn.singno.commonsframework.validation.constraintvalidators.ForbiddenWordsValidator;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**<p>名称：ForbiddenWords.java</p>
 * <p>描述：禁止敏感词的注解</p>
 * <pre>
 *         
 * </pre>
 * @author 周光暖
 * @date 2014年8月13日 下午10:29:34
 * @version 1.0.0
 */
@Documented  		  
@Constraint(validatedBy = ForbiddenWordsValidator.class)  
@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})  
@Retention(RUNTIME)  
public @interface ForbiddenWords {  
  
    //默认错误消息  
    String message() default "{user.name.forbiddenWords}";  
  
    //分组  
    Class<?>[] groups() default { };  
  
    //负载  
    Class<? extends Payload>[] payload() default { };  
  
    //指定多个时使用  
    @Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })  
    @Retention(RUNTIME)  
    @Documented  
    @interface List {  
    	ForbiddenWords[] value();  
    }  
}  
