package cn.singno.commonsframework.bind.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cn.singno.commonsframework.constants.ApplicationConst;

/**
 * 名称：CurrentUser.java<br>
 * 描述：绑定当前登录的用户<br>
 * <pre>不同于@ModelAttribute</pre>
 * @author 周光暖
 * @date 2015-3-23 下午9:54:51
 * @version 1.0.0
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {

    /**
     * 当前用户在request中的名字
     *
     * @return
     */
    String value() default ApplicationConst.CURRENT_USER;

}
