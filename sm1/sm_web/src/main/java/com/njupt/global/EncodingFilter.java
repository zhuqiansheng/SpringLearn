package com.njupt.global;

import javax.servlet.*;
import java.io.IOException;

/**
 * 用于过滤编码
 */
public class EncodingFilter implements Filter {

    //默认编码
    private String encoding ="UTF-8";

    public void init(FilterConfig filterConfig) throws ServletException {
        //用filterConfig 获取 初始参数值
        if(filterConfig.getInitParameter("ENCODING")!=null)
            encoding = filterConfig.getInitParameter("ENCODING");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 设置编码为获取的 encoding 值
        servletRequest.setCharacterEncoding(encoding);
        servletResponse.setCharacterEncoding(encoding);
        //进行后面的后滤  (如果有)
        filterChain.doFilter(servletRequest,servletResponse);
    }

    public void destroy() {
        encoding=null;
    }
}
