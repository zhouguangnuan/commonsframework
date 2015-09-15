///**<p>项目名：</p>
// * <p>包名：	cn.singno.commonsframework.validation.constraintvalidators</p>
// * <p>文件名：CheckPasswordValidator.java</p>
// * <p>版本信息：</p>
// * <p>日期：2014年8月14日-下午3:59:32</p>
// * Copyright (c) 2014singno公司-版权所有
// */
//package cn.singno.commonsframework.validation.constraintvalidators;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//
//import org.springframework.util.StringUtils;
//
//import cn.singno.commonsframework.validation.bean.User2;
//import cn.singno.commonsframework.validation.constraints.CheckPassword;
//
///**<p>名称：CheckPasswordValidator.java</p>
// * <p>描述：检查密码输入是否一致的验证器</p>
// * <pre>
// *     类级别验证器    
// * </pre>
// * @author 周光暖
// * @date 2014年8月14日 下午3:59:32
// * @version 1.0.0
// */
//public class CheckPasswordValidator implements ConstraintValidator<CheckPassword, User2> {  
//	  
//    @Override  
//    public void initialize(CheckPassword constraintAnnotation) {  
//    }  
//  
//    @Override  
//    public boolean isValid(User2 user, ConstraintValidatorContext context) {  
//        if(user == null) {  
//            return true;  
//        }  
//  
//        //没有填密码  
//        if(!StringUtils.hasText(user.getPassword())) {  
//            context.disableDefaultConstraintViolation();  
//            context.buildConstraintViolationWithTemplate("{password.null}")  
//                    .addPropertyNode("password")  
//                    .addConstraintViolation();  
//            return false;  
//        }  
//  
//        if(!StringUtils.hasText(user.getConfirmation())) {  
//            context.disableDefaultConstraintViolation();  
//            context.buildConstraintViolationWithTemplate("{password.confirmation.null}")  
//                    .addPropertyNode("confirmation")  
//                    .addConstraintViolation();  
//            return false;  
//        }  
//  
//        //两次密码不一样  
//        if (!user.getPassword().trim().equals(user.getConfirmation().trim())) {  
//            context.disableDefaultConstraintViolation();  
//            context.buildConstraintViolationWithTemplate("{password.confirmation.error}")  
//                    .addPropertyNode("confirmation")  
//                    .addConstraintViolation();  
//            return false;  
//        }  
//        return true;  
//    }  
//}  