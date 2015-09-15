/**<p>项目名：</p>
 * <p>包名：	cn.singno.commonsframework.validation.constraintvalidators</p>
 * <p>文件名：PropertyScriptAssertValidator.java</p>
 * <p>版本信息：</p>
 * <p>日期：2014年8月16日-下午3:07:24</p>
 * Copyright (c) 2014singno公司-版权所有
 */
package cn.singno.commonsframework.validation.constraintvalidators;

import static org.hibernate.validator.internal.util.logging.Messages.MESSAGES;

import javax.script.ScriptException;
import javax.validation.ConstraintDeclarationException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.validator.internal.util.Contracts;
import org.hibernate.validator.internal.util.logging.Log;
import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.hibernate.validator.internal.util.scriptengine.ScriptEvaluator;
import org.hibernate.validator.internal.util.scriptengine.ScriptEvaluatorFactory;

import cn.singno.commonsframework.validation.constraints.PropertyScriptAssert;

/**<p>名称：PropertyScriptAssertValidator.java</p>
 * <p>描述：</p>
 * <pre>
 *         
 * </pre>
 * @author 周光暖
 * @date 2014年8月16日 下午3:07:24
 * @version 1.0.0
 */
public class PropertyScriptAssertValidator implements ConstraintValidator<PropertyScriptAssert, Object> {  
	  
    private static final Log log = LoggerFactory.make();  
  
    private String script;  
    private String languageName;  
    private String alias;  
    private String property;  
    private String message;  
  
    public void initialize(PropertyScriptAssert constraintAnnotation) {  
        validateParameters( constraintAnnotation );  
  
        this.script = constraintAnnotation.script();  
        this.languageName = constraintAnnotation.lang();  
        this.alias = constraintAnnotation.alias();  
        this.property = constraintAnnotation.property();  
        this.message = constraintAnnotation.message();  
    }  
  
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {  
  
        Object evaluationResult;  
        ScriptEvaluator scriptEvaluator;  
  
        try {  
            ScriptEvaluatorFactory evaluatorFactory = ScriptEvaluatorFactory.getInstance();  
            scriptEvaluator = evaluatorFactory.getScriptEvaluatorByLanguageName( languageName );  
        }  
        catch ( ScriptException e ) {  
            throw new ConstraintDeclarationException( e );  
        }  
  
        try {  
            evaluationResult = scriptEvaluator.evaluate( script, value, alias );  
        }  
        catch ( ScriptException e ) {  
            throw log.getErrorDuringScriptExecutionException( script, e );  
        }  
  
        if ( evaluationResult == null ) {  
            throw log.getScriptMustReturnTrueOrFalseException( script );  
        }  
        if ( !( evaluationResult instanceof Boolean ) ) {  
            throw log.getScriptMustReturnTrueOrFalseException(  
                    script,  
                    evaluationResult,  
                    evaluationResult.getClass().getCanonicalName()  
            );  
        }  
  
        if(Boolean.FALSE.equals(evaluationResult)) {  
            constraintValidatorContext.disableDefaultConstraintViolation();  
            constraintValidatorContext  
                    .buildConstraintViolationWithTemplate(message)  
                    .addPropertyNode(property)  
                    .addConstraintViolation();  
        }  
  
        return Boolean.TRUE.equals( evaluationResult );  
    }  
  
    private void validateParameters(PropertyScriptAssert constraintAnnotation) {  
        Contracts.assertNotEmpty( constraintAnnotation.script(), MESSAGES.parameterMustNotBeEmpty( "script" ) );  
        Contracts.assertNotEmpty( constraintAnnotation.lang(), MESSAGES.parameterMustNotBeEmpty( "lang" ) );  
        Contracts.assertNotEmpty( constraintAnnotation.alias(), MESSAGES.parameterMustNotBeEmpty( "alias" ) );  
        Contracts.assertNotEmpty( constraintAnnotation.property(), MESSAGES.parameterMustNotBeEmpty( "property" ) );  
        Contracts.assertNotEmpty( constraintAnnotation.message(), MESSAGES.parameterMustNotBeEmpty( "message" ) );  
    }  
}
