package com.yiye.model;


/**
 * Token
 * 
 * @author tony
 * 
 */
public class Token {

	// userNick,userId,token_type,access_token,refresh_token,expire_in,r1_expires_in,r2_expires_in,w1_expires_in,w2_expires_in

	private String userNick;
	private String userId;
	private String token_type;
	private String access_token;
	private String refresh_token;
	private Long expire_in;//存放的是绝对的时间戳
	private Long r1_expire_in;
	private Long r2_expire_in;
	private Long w1_expire_in;
	private Long w2_expire_in;

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public Long getExpire_in() {
		return expire_in;
	}

	public void setExpire_in(Long expire_in) {
		this.expire_in = expire_in;
	}

	public Long getR1_expire_in() {
		return r1_expire_in;
	}

	public void setR1_expire_in(Long r1_expire_in) {
		this.r1_expire_in = r1_expire_in;
	}

	public Long getR2_expire_in() {
		return r2_expire_in;
	}

	public void setR2_expire_in(Long r2_expire_in) {
		this.r2_expire_in = r2_expire_in;
	}

	public Long getW1_expire_in() {
		return w1_expire_in;
	}

	public void setW1_expire_in(Long w1_expire_in) {
		this.w1_expire_in = w1_expire_in;
	}

	public Long getW2_expire_in() {
		return w2_expire_in;
	}

	public void setW2_expire_in(Long w2_expire_in) {
		this.w2_expire_in = w2_expire_in;
	}

	public static Token convert(LoginToken loginToken) {
		Token token = new Token();
		token.setUserNick(loginToken.getTaobao_user_nick());
		token.setUserId(loginToken.getTaobao_user_id());
		token.setToken_type(loginToken.getToken_type());
		token.setAccess_token(loginToken.getAccess_token());
		token.setRefresh_token(loginToken.getRefresh_token());

		long now = System.currentTimeMillis();
		long expire_in_ts = (long) loginToken.getExpires_in() + now;
		token.setExpire_in(expire_in_ts);

		long r1_expire_in_ts = (long) loginToken.getR1_expires_in() + now;
		token.setR1_expire_in(r1_expire_in_ts);

		long r2_expire_in_ts = (long) loginToken.getR2_expires_in() + now;
		token.setR2_expire_in(r2_expire_in_ts);

		long w1_expire_in_ts = (long) loginToken.getW1_expires_in() + now;
		token.setW1_expire_in(w1_expire_in_ts);

		long w2_expire_in_ts = (long) loginToken.getW2_expires_in() + now;
		token.setW2_expire_in(w2_expire_in_ts);

		return token;
	}

}
