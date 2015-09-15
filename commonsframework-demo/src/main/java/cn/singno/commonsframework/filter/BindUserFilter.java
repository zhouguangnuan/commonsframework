package cn.singno.commonsframework.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import cn.singno.commonsframework.constants.ApplicationConst;
import cn.singno.commonsframework.module.app.service.UserService;

/**
 * 名称：BindUserFilter.java<br>
 * 描述：绑定登录用户过滤器<br>
 * <pre>绑定当前登录用户到request</pre>
 * @author 周光暖
 * @date 2015-3-23 下午9:57:10
 * @version 1.0.0
 */
public class BindUserFilter extends PathMatchingFilter {

    @Autowired
    private UserService userService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        String username = (String)SecurityUtils.getSubject().getPrincipal();
        request.setAttribute(ApplicationConst.CURRENT_USER, userService.findByUserName(username));
        return true;
    }
}
