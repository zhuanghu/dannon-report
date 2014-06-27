package com.yiye.dannon.dao;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.yiye.model.Item;



@RunWith(JUnit4.class)
public class ItemDaoTest extends TestCase {

	ItemDao dao;
	
	@Before
	public void setUp() {
		dao = new ItemDao();
	}

	@After
	public void tearDown() {

	}

	@Test
	public void testSave() {
		Item item = new Item();
		item.setItemId(2000100006710l);
		item.setTitle("沙箱测试:休闲女装2013最新款");
		dao.save(item);
	}
}
