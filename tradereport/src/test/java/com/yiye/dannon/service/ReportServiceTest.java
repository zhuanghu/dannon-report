package com.yiye.dannon.service;

import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.alibaba.fastjson.JSON;
import com.yiye.model.DateRange;
import com.yiye.model.SellerStatistics;

@RunWith(JUnit4.class)
public class ReportServiceTest extends TestCase {

	ReportService service;

	@Before
	public void setUp() {
		service = new ReportService();
	}

	@After
	public void tearDown() {

	}

	@Test
	public void testGetSellerReport() throws Exception {
		Date start = SimpleDateFormat.getDateTimeInstance().parse("2013-09-01 00:00:00");
		Date end = SimpleDateFormat.getDateTimeInstance().parse("2013-09-30 23:59:59");
		DateRange created = new DateRange(start.getTime(), end.getTime());
		List<SellerStatistics> list = service.getSellerReport(created);
		for (SellerStatistics sellerStatistics : list) {
			System.out.println(JSON.toJSON(sellerStatistics));
		}
	}
}
