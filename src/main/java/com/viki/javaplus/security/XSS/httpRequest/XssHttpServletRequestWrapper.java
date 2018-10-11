package com.viki.javaplus.security.XSS.httpRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
    HttpServletRequest request;
    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        this.request=request;
    }

    @Override
    public String getParameter(String name) {
        String olvalue=super.getParameter(name);
        if(!StringUtils.isEmpty(olvalue)){
             olvalue=StringEscapeUtils.escapeHtml3(olvalue);
        }
        return olvalue;
    }
}
