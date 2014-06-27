package com.yiye.dannon.web;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.yiye.model.SellerStatistics;
import com.yiye.model.TbOrder;
import com.yiye.model.TokenCache;
import com.yiye.utils.TimeUtils;

import freemarker.template.Template;

/**
 * 
 * Sample: http://sandbox.yiye.com/report/
 * @author tony
 * 
 */
@SuppressWarnings("serial")
public class SellerReportServlet extends FreemarkerBaseServlet {

	private static Logger logger_report_tracker = LoggerFactory.getLogger("report_tracker");
	
	protected void service(HttpServletRequest req, HttpServletResponse res) {
		try {
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/html");
			
			String created_start = req.getParameter("created_start");
			String created_end = req.getParameter("created_end");
			
			Criteria criteria = new Criteria();

			Date dt_start = null;
			Date dt_end = null;
			if (!StringUtils.isEmpty(created_start) && !StringUtils.isEmpty(created_end)) {
				dt_start = SimpleDateFormat.getDateTimeInstance().parse(created_start);
				dt_end = SimpleDateFormat.getDateTimeInstance().parse(created_end);
			} else {
				dt_end = new Date();
				Calendar c = Calendar.getInstance();
				c.add(Calendar.DAY_OF_YEAR, -30);
				dt_start = c.getTime();
			}
			DateRange dateRange = new DateRange(dt_start.getTime(), dt_end.getTime());
			criteria.setCreated(dateRange);
			
			ReportService service = new ReportService();
			List<SellerStatistics> ss = service.getSellerReport(dateRange);
			
			Map<String, Object> root = new HashMap<String, Object>();
			root.put("sellerstatistics", ss);
			
			Template temp = cfg.getTemplate("allseller.ftl");
			temp.process(root, res.getWriter());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
