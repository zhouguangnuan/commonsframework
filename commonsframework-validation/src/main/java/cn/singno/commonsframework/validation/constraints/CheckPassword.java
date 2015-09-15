///**<p>项目名：</p>
// * <p>包名：	cn.singno.commonsframework.validation.constraints</p>
// * <p>文件名：CheckPassword.java</p>
// * <p>版本信息：</p>
// * <p>日期：2014年8月14日-下午3:58:08</p>
// * Copyright (c) 2014singno公司-版权所有
// */
//package cn.singno.commonsframework.validation.constraints;
//
//import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
//import static java.lang.annotation.ElementType.FIELD;
//import static java.lang.annotation.ElementType.METHOD;
//import static java.lang.annotation.ElementType.PARAMETER;
//import static java.lang.annotation.ElementType.TYPE;
//import static java.lang.annotation.RetentionPolicy.RUNTIME;
//
//import java.lang.annotation.Documented;
//import java.lang.annotation.Retention;
//import java.lang.annotation.Target;
//
//import javax.validation.Constraint;
//import javax.validation.Payload;
//
//import cn.singno.commonsframework.validation.constraintvalidators.CheckPasswordValidator;
//
///**<p>名称：CheckPassword.java</p>
// * <p>描述：检查密码输入是否一致的注解</p>
// * <pre>
// *         
// * </pre>
// * @author 周光暖
// * @date 2014年8月14日 下午3:58:08
// * @version 1.0.0
// */
//@Target({ TYPE, ANNOTATION_TYPE})  
//@Retention(RUNTIME)  
//@Constraint(validatedBy = CheckPasswordValidator.class)  
//@Documented  
//public @interface CheckPassword {  
//  
//    //默认错误消息  
//    String message() default "";  
//  
//    //分组  
//    Class<?>[] groups() default { };  
//  
//    //负载  
//    Class<? extends Payload>[] payload() default { };  
//  
//    //指定多个时使用  
//    @Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })  
//    @Retention(RUNTIME)  
//    @Documented  
//    @interface List {  
//        CheckPassword[] value();  
//    }  
//}  