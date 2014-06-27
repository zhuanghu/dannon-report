package com.yiye.dannon.web;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yiye.dannon.service.ReportService;
import com.yiye.model.Criteria;
import com.yiye.model.DateRange;
import com.yiye.model.LoginToken;
import com.yiye.model.TbOrder;
import com.yiye.model.TokenCache;

import freemarker.template.Template;

/**
 * 
 * Sample: http://sandbox.yiye.com/token
 * @author tony
 * 
 */
@SuppressWarnings("serial")
public class DashboardServlet extends FreemarkerBaseServlet {

	private static Logger logger_report_tracker = LoggerFactory.getLogger("report_tracker");
	
	protected void service(HttpServletRequest req, HttpServletResponse res) {
		try {
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/html");
			
			String sellerNick = req.getParameter("nick");
			String title = req.getParameter("title");
			String tradeId = req.getParameter("tradeId");
			String orderId = req.getParameter("orderId");
			String itemId = req.getParameter("itemId");
			String created_start = req.getParameter("created_start");
			String created_end = req.getParameter("created_end");
			//String refundStatus = req.getParameter("refundStatus");
			
			Criteria criteria = new Criteria();
			if (!StringUtils.isEmpty(sellerNick)) criteria.setSellerNick(sellerNick);
			if (!StringUtils.isEmpty(title)) criteria.setTitle(title);
			if (!StringUtils.isEmpty(tradeId)) criteria.setTradeId(Long.parseLong(tradeId));
			if (!StringUtils.isEmpty(orderId)) criteria.setOrderId(Long.parseLong(orderId));
			if (!StringUtils.isEmpty(itemId)) criteria.setItemId(Long.parseLong(itemId));
			if (!StringUtils.isEmpty(created_start) && !StringUtils.isEmpty(created_end)) {
				Date dt_start = SimpleDateFormat.getInstance().parse(created_start);
				Date dt_end = SimpleDateFormat.getInstance().parse(created_end);
				DateRange dateRange = new DateRange(dt_start.getTime(), dt_end.getTime());
				criteria.setCreated(dateRange);
			}
			
			ReportService service = new ReportService();
			List<TbOrder> tborders = service.getTbOrders(criteria);
			
			Map<String, Object> root = new HashMap<String, Object>();
			TokenCache cache = TokenCache.getInstance();
			LoginToken token = cache.get(sellerNick);
			root.put("nick", sellerNick);
			root.put("token", token);
			root.put("tborders", tborders);
			
			
			
			Template temp = cfg.getTemplate("dashboard.ftl");
			temp.process(root, res.getWriter());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
