package com.njupt.global;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {

    //默认编码
    private String encoding ="UTF-8";

    public void init(FilterConfig filterConfig) throws ServletException {
        if(filterConfig.getInitParameter("ENCODING")!=null)
            encoding = filterConfig.getInitParameter("ENCODING");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 将servletResponse 和  servletRequest 的编码都设置为获取的 encoding
        servletRequest.setCharacterEncoding(encoding);
        servletResponse.setCharacterEncoding(encoding);
        //为servletResponse 和  servletRequest过滤
        filterChain.doFilter(servletRequest,servletResponse);
    }

    public void destroy() {
        encoding=null;
    }
}
