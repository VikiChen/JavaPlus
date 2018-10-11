package com.viki.javaplus.security.PreventLink.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


//实现防盗链
@WebFilter(filterName = "imageFilter",urlPatterns = "/imgs/*")
public class ImageFilter implements Filter {

    @Value("${domain.name}")
    private String domainName;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1.获取请求头来源
        HttpServletRequest req=(HttpServletRequest)servletRequest;
        String referer=req.getHeader("Referer");
        if(!StringUtils.isEmpty(referer)){
            servletRequest.getRequestDispatcher("/imgs/error.png").forward(req,servletResponse);
            return;
        }
        //2.判断请求头中域名是否与限制的域名一致
        String url = getDomain(referer);
        if (url.equals(domainName)){
            return;
        }
        //直接放行图片
        filterChain.doFilter(servletRequest,servletResponse);
    }


    /**
	 * 获取url对应的域名
	 */
	public String getDomain(String url) {
		String result = "";
		int j = 0, startIndex = 0, endIndex = 0;
		for (int i = 0; i < url.length(); i++) {
			if (url.charAt(i) == '/') {
				j++;
				if (j == 2)
					startIndex = i;
				else if (j == 3)
					endIndex = i;
			}

		}
		result = url.substring(startIndex + 1, endIndex);
		return result;
	}
    @Override
    public void destroy() {

    }
}
