package com.viki.javaplus.security.XSS.filter;

import com.viki.javaplus.security.XSS.httpRequest.XssHttpServletRequestWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "xssFilter",urlPatterns = "/*")
public class XssFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
          //程序防止xss 攻击
          //使用过滤器拦截所有参数
        HttpServletRequest req =(HttpServletRequest)servletRequest;
          //重新getParamteter方法
        //使用StringEscapeUtils.escapeHtml3()转换特殊字符
        XssHttpServletRequestWrapper xssHttpServletRequestWrapper = new XssHttpServletRequestWrapper(req);
        filterChain.doFilter(xssHttpServletRequestWrapper,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
