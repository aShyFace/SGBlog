package com.example.filter;

import org.apache.tomcat.util.modeler.BaseAttributeFilter;

/**
 * @ClassName: LoginAuthenticationFilter
 * @Description: 登录放行过滤器
 * @author: Zhi
 * @date: 2023/4/6 下午4:46
 */
public class LoginAuthenticationFilter extends BaseAttributeFilter {
    /**
     * Construct a new filter that accepts only the specified attribute
     * name.
     *
     * @param name Name of the attribute to be accepted by this filter, or
     *             <code>null</code> to accept all attribute names
     */
    public LoginAuthenticationFilter(String name) {
        super(name);
    }


}
