/**<p>项目名：</p>
 * <p>包名：	cn.singno.commonsframework.validation.constraintvalidators</p>
 * <p>文件名：CrossParameterValidator.java</p>
 * <p>版本信息：</p>
 * <p>日期：2014年8月16日-下午4:09:07</p>
 * Copyright (c) 2014singno公司-版权所有
 */
package cn.singno.commonsframework.validation.constraintvalidators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

import cn.singno.commonsframework.validation.constraints.CrossParameter;

/**<p>名称：CrossParameterValidator.java</p>
 * <p>描述：跨参数验证器</p>
 * <pre>
 *         
 * </pre>
 * @author 周光暖
 * @date 2014年8月16日 下午4:09:07
 * @version 1.0.0
 */
@SupportedValidationTarget(ValidationTarget.PARAMETERS)  
public class CrossParameterValidator implements ConstraintValidator<CrossParameter, Object[]> {  
  
    @Override  
    public void initialize(CrossParameter constraintAnnotation) {  
    }  
  
    @Override  
    public boolean isValid(Object[] value, ConstraintValidatorContext context) {  
        if(value == null || value.length != 2) {  
            throw new IllegalArgumentException("must have two args");  
        }  
        if(value[0] == null || value[1] == null) {  
            return true;  
        }  
        if(value[0].equals(value[1])) {  
            return true;  
        }  
        return false;  
    }  
}  