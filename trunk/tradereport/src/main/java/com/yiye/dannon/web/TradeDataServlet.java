package com.yiye.dannon.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yiye.dannon.service.TradeService;
import com.yiye.model.LoginToken;
import com.yiye.model.TokenCache;
import com.yiye.utils.AppConfig;

import freemarker.template.Template;

/**
 * 
 * Sample: http://sandbox.yiye.com/report/gettrradedata
 * @author tony
 * 
 */
@SuppressWarnings("serial")
public class TradeDataServlet extends FreemarkerBaseServlet {

	private static Logger logger_report_tracker = LoggerFactory.getLogger("report_tracker");
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) {
		try {
			res.setCharacterEncoding("UTF-8");
			
			String nick = req.getParameter("nick");
			System.out.println("get trade data for " + nick);
			TradeService service = new TradeService();
			service.getRemoteTradeData(nick);
			String url = "http://" + AppConfig.getProperty("HOST") + "/report/dashboard?nick=" + nick;
			res.sendRedirect(url);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
