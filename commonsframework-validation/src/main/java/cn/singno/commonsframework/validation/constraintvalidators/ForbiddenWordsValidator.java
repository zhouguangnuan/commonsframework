/**<p>项目名：</p>
 * <p>包名：	cn.singno.commonsframework.validation.constraintvalidators</p>
 * <p>文件名：ForbiddenWordsValidator.java</p>
 * <p>版本信息：</p>
 * <p>日期：2014年8月13日-下午10:38:45</p>
 * Copyright (c) 2014singno公司-版权所有
 */
package cn.singno.commonsframework.validation.constraintvalidators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;

import cn.singno.commonsframework.validation.constraints.ForbiddenWords;

/**<p>名称：ForbiddenWordsValidator.java</p>
 * <p>描述：禁止敏感词的验证器</p>
 * <pre>
 *         
 * </pre>
 * @author 周光暖
 * @date 2014年8月13日 下午10:38:45
 * @version 1.0.0
 */
public class ForbiddenWordsValidator implements ConstraintValidator<ForbiddenWords, String> {  
  
    private String[] forbiddenWords = {"admin"};  
    
    @Override  
    public void initialize(ForbiddenWords constraintAnnotation) {  
    }  
  
    @Override  
    public boolean isValid(String value, ConstraintValidatorContext context) {  
        if(StringUtils.isEmpty(value)) {  
            return true;  
        }  
  
        for(String word : forbiddenWords) {  
            if(value.contains(word)) {  
            	((ConstraintValidatorContextImpl)context).getConstraintDescriptor().getAttributes().put("forbiddenWord", word);  
                return false;//验证失败  
            }  
        }  
        
        /*for(String word : forbiddenWords) {  
            if(value.contains(word)) {  
                ((ConstraintValidatorContextImpl)context).getConstraintDescriptor().getAttributes().put("word", word);  
                return false;//验证失败  
            }  
        }  */
        
        return true;  
    }  
}
