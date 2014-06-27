package com.yiye.dannon.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yiye.dannon.service.AuthService;
import com.yiye.model.LoginToken;
import com.yiye.model.TokenCache;
import com.yiye.utils.AppConfig;

import freemarker.template.Template;

/**
 * 
 * http://sandbox.yiye.com/auth
 * @author tony
 * 
 */
@SuppressWarnings("serial")
public class AuthServlet extends FreemarkerBaseServlet {

	private static Logger logger_trade_tracker = LoggerFactory.getLogger("trade_tracker");
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) {
		try {
			res.setCharacterEncoding("UTF-8");
			String authCode = req.getParameter("code");
			System.out.println(authCode);
			Map<String, Object> root = new HashMap<String, Object>();
			if (authCode == null) {
				String errorcode = req.getParameter("error");
				String errordesc = req.getParameter("error_description");
				logger_trade_tracker.info("[auth] error code=" + errorcode + ", error desc=" + errordesc);
				root.put("errorcode", errorcode);
				root.put("errordesc", errordesc);
				Template temp = cfg.getTemplate("auth_fail.ftl");
				temp.process(root, res.getWriter());
			} else {
				logger_trade_tracker.info("[auth] auth code=" + authCode);
				AuthService service = new AuthService();
				LoginToken token = service.getToken(authCode);
				System.out.println(token);
				TokenCache cache = TokenCache.getInstance();
				cache.put(token.getTaobao_user_nick(), token);
				
				String url = "http://" + AppConfig.getProperty("HOST") + "/report/dashboard?nick=" + token.getTaobao_user_nick();
				res.sendRedirect(url);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
