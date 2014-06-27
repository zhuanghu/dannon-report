package com.yiye.dannon.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.yiye.model.Criteria;
import com.yiye.model.DateRange;
import com.yiye.model.LoginToken;
import com.yiye.model.TbOrder;
import com.yiye.utils.*;


/**
 * token
 * @author tony
 *
 */
public class TokenDao {
	
	public void save(LoginToken token) {
		try {
			Connection conn = BoneCPManager.getConnectionPool().getConnection();
			conn.setAutoCommit(true);

			PreparedStatement stat = conn.prepareStatement("insert into dannon_token (userNick,userId,token_type,access_token,refresh_token,expire_in,r1_expires_in,r2_expires_in,w1_expires_in,w2_expires_in) values (?,?,?,?,?,?,?,?,?,?)");
			stat.setString(1, token.getTaobao_user_nick());
			stat.setString(2, token.getTaobao_user_id());
			stat.setString(3, token.getToken_type());
			stat.setString(4, token.getAccess_token());
			stat.setString(5, token.getRefresh_token());
			long expire_in_ts = (long) token.getExpires_in() + System.currentTimeMillis();
			stat.setTimestamp(6, new Timestamp(expire_in_ts));
			long r1_expire_in_ts = (long) token.getR1_expires_in() + System.currentTimeMillis();
			stat.setTimestamp(7, new Timestamp(r1_expire_in_ts));
			long r2_expire_in_ts = (long) token.getR2_expires_in() + System.currentTimeMillis();
			stat.setTimestamp(8, new Timestamp(r2_expire_in_ts));
			long w1_expire_in_ts = (long) token.getW1_expires_in() + System.currentTimeMillis();
			stat.setTimestamp(9, new Timestamp(w1_expire_in_ts));
			long w2_expire_in_ts = (long) token.getW2_expires_in() + System.currentTimeMillis();
			stat.setTimestamp(10, new Timestamp(w2_expire_in_ts));
			
			stat.executeUpdate();
			stat.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
 	}
	
	public void delete(String accessToken) {
		try {
			Connection conn = BoneCPManager.getConnectionPool().getConnection();
			PreparedStatement stat = conn.prepareStatement("delete from dannon_token where access_token=?");
			stat.setString(1, accessToken);
			stat.executeUpdate();
			stat.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<LoginToken> findAllToken() {
		List<LoginToken> list = new ArrayList<LoginToken>();
		try {
			Connection conn = BoneCPManager.getConnectionPool().getConnection();			
			PreparedStatement stat = conn.prepareStatement("select * from dannon_token");
			ResultSet rs = stat.executeQuery();
			
			while (rs.next()) {
				LoginToken token = new LoginToken();
				//userNick,userId,token_type,access_token,refresh_token,expire_in,r1_expires_in,r2_expires_in,w1_expires_in,w2_expires_in
				token.setTaobao_user_nick(rs.getString("userNick"));
				token.setTaobao_user_id(rs.getString("userId"));
				token.setToken_type(rs.getString("token_type"));
				token.setAccess_token(rs.getString("access_token"));
				token.setRefresh_token(rs.getString("refresh_token"));
				
				list.add(token);
			}
			
			stat.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
