package cn.singno.commonsframework.validation.constraintvalidators;

import static org.hibernate.validator.internal.util.logging.Messages.MESSAGES;

import javax.script.ScriptException;
import javax.validation.ConstraintDeclarationException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

import org.hibernate.validator.internal.util.Contracts;
import org.hibernate.validator.internal.util.logging.Log;
import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.hibernate.validator.internal.util.scriptengine.ScriptEvaluator;
import org.hibernate.validator.internal.util.scriptengine.ScriptEvaluatorFactory;

import cn.singno.commonsframework.validation.constraints.CrossParameterScriptAssert;

/**
 * <p>名称：CrossParameterScriptAssertValidator.java</p>
 * <p>描述：</p>
 * <pre>
 *         
 * </pre>
 * @author 周光暖
 * @date 2014-11-11 下午9:14:44
 * @version 1.0.0
 */
@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class CrossParameterScriptAssertValidator implements ConstraintValidator<CrossParameterScriptAssert, Object[]> {

    private static final Log log = LoggerFactory.make();

    private String script;
    private String languageName;
    private String alias;

    public void initialize(CrossParameterScriptAssert constraintAnnotation) {
        validateParameters( constraintAnnotation );

        this.script = constraintAnnotation.script();
        this.languageName = constraintAnnotation.lang();
        this.alias = constraintAnnotation.alias();
    }

    public boolean isValid(Object[] value, ConstraintValidatorContext constraintValidatorContext) {

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
        return Boolean.TRUE.equals( evaluationResult );
    }

    private void validateParameters(CrossParameterScriptAssert constraintAnnotation) {
        Contracts.assertNotEmpty(constraintAnnotation.script(), MESSAGES.parameterMustNotBeEmpty("script"));
        Contracts.assertNotEmpty( constraintAnnotation.lang(), MESSAGES.parameterMustNotBeEmpty( "lang" ) );
        Contracts.assertNotEmpty( constraintAnnotation.alias(), MESSAGES.parameterMustNotBeEmpty( "alias" ) );
    }
}
