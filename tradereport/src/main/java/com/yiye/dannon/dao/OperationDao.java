package com.yiye.dannon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yiye.model.Operation;
import com.yiye.utils.BoneCPManager;


/**
 * 操作记录
 * @author tony
 *
 */
public class OperationDao {
	
	public void save(Operation operation) {
		try {
			Connection conn = BoneCPManager.getConnectionPool().getConnection();
			PreparedStatement stat = conn.prepareStatement("insert into dannon_operation (name,conent,operator,optime) values (?, ?, ?, ?)");
			stat.setString(1, operation.getName());
			stat.setString(2, operation.getContent());
			stat.setString(3, operation.getOperator());
			stat.setTimestamp(4, new Timestamp(operation.getOptime().getTime()));
			stat.executeUpdate();
			conn.commit();
			stat.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
 	}
	
	public List<Operation> findOperations(Date start, Date end) {
		List<Operation> operations = new ArrayList<Operation>();
		try {
			Connection conn = BoneCPManager.getConnectionPool().getConnection();
			PreparedStatement stat = conn.prepareStatement("select * from operation where optime between ? and ?");
			stat.setTimestamp(1, new Timestamp(start.getTime()));
			stat.setTimestamp(2, new Timestamp(end.getTime()));
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				Operation operation = new Operation();
				operation.setName(rs.getString("name"));
				operation.setContent(rs.getString("content"));
				operation.setOperator(rs.getString("operator"));
				operation.setOptime(rs.getTimestamp("optime"));
				operations.add(operation);
			}
			conn.commit();
			stat.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return operations;
	}
	
}
