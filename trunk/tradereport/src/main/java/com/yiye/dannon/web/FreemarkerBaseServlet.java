package com.yiye.dannon.web;

import java.util.Locale;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.ObjectWrapper;


/**
 * Freemarker 基类,实现Freemarker基本配置，凡事需要Freemarker的Servlet 直接继承此类
 * @author xiaozhou
 *
 */
@SuppressWarnings("serial")
public class FreemarkerBaseServlet extends HttpServlet {
	protected Configuration cfg; //freemarker 配置
	
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		cfg = new Configuration();
		cfg.setServletContextForTemplateLoading(servletConfig.getServletContext(), "/WEB-INF/freemarker");
		cfg.setObjectWrapper(ObjectWrapper.BEANS_WRAPPER);
		cfg.setDefaultEncoding("UTF-8");
	}
}
