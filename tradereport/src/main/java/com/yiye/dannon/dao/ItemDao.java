package com.yiye.dannon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yiye.model.Item;
import com.yiye.utils.BoneCPManager;


/**
 * 指定商品
 * @author tony
 *
 */
public class ItemDao {
	
	public void save(Item item) {
		try {
			Connection conn = BoneCPManager.getConnectionPool().getConnection();
			conn.setAutoCommit(true);
			PreparedStatement stat = conn.prepareStatement("insert into dannon_item (itemId,title) values (?,?)");
			stat.setLong(1, item.getItemId());
			stat.setString(2, item.getTitle());
			stat.executeUpdate();
			stat.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
 	}
	
	public void delete(Long itemId) {
		try {
			Connection conn = BoneCPManager.getConnectionPool().getConnection();
			PreparedStatement stat = conn.prepareStatement("delete from dannon_item where itemId=?");
			stat.setLong(1, itemId);
			stat.executeUpdate();
			stat.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
 	}
	
	public Item findItem(Long itemId) {
		Item item = null;
		try {
			Connection conn = BoneCPManager.getConnectionPool().getConnection();
			PreparedStatement stat = conn.prepareStatement("select * from dannon_item where itemId = ?");
			stat.setLong(1, itemId);
			ResultSet rs = stat.executeQuery();
			
			if (rs.next()) {
				item = new Item();
				item.setItemId(rs.getLong("itemId"));
				item.setTitle(rs.getString("title"));
			}
			conn.commit();
			stat.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item;
	}
	
	public List<Item> findItems(String title) {
		List<Item> list = new ArrayList<Item>();
		try {
			Connection conn = BoneCPManager.getConnectionPool().getConnection();
			PreparedStatement stat = conn.prepareStatement("select * from dannon_item where title = ?");
			stat.setString(1, title);
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				Item item = new Item();
				item.setItemId(rs.getLong("itemId"));
				item.setTitle(rs.getString("title"));
				list.add(item);
			}
			conn.commit();
			stat.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Item> findAllItems() {
		List<Item> list = new ArrayList<Item>();
		try {
			Connection conn = BoneCPManager.getConnectionPool().getConnection();
			PreparedStatement stat = conn.prepareStatement("select * from dannon_item");
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				Item item = new Item();
				item.setItemId(rs.getLong("itemId"));
				item.setTitle(rs.getString("title"));
				list.add(item);
			}
			conn.commit();
			stat.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
