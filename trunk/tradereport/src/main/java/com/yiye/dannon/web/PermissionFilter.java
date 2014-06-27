package com.yiye.dannon.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.yiye.model.TokenCache;
import com.yiye.utils.AppConfig;

import freemarker.template.Template;

/**
 * 
 * @author tony
 * 
 */
@SuppressWarnings("serial")
public class PermissionFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("init PermissionFilter");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		TokenCache cache = TokenCache.getInstance();
		if (cache.map().size() > 0) {
			chain.doFilter(request, response);
			return;
		} else {
			res.sendRedirect("http://" + AppConfig.getProperty("HOST") + "/index.html");
		}
	}

	@Override
	public void destroy() {
		System.out.println("destroy AuthFilter");
	}
	
}
