package com.yiye.dannon.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.taobao.api.internal.util.WebUtils;
import com.yiye.model.Config;
import com.yiye.model.LoginToken;
import com.yiye.utils.AppConfig;

/**
 * 获得
 * @author tony
 *
 */
public class AuthService {
	
	private static Logger logger_auth_tracker = LoggerFactory.getLogger("auth_tracker");
	
	/**
	 * post request for token by code
	 * @param code 
	 */
	public LoginToken getToken(String authCode) {
		String redirect_uri = "http://" + AppConfig.getProperty("HOST") + "/start.html";
		Map<String, String> param = new HashMap<String, String>();
		param.put("grant_type", "authorization_code");
		param.put("code", authCode);
		param.put("client_id", Config.APPKEY);
		param.put("client_secret", Config.APPSECRET);
		param.put("redirect_uri", redirect_uri);
		param.put("view", "web");
		//param.put("state", Config.AUTH_DEFAULT_STAT);
		try {
			String responseJson = WebUtils.doPost(Config.TOKEN_URL, param, 3000, 3000);
			LoginToken token = JSON.parseObject(responseJson, LoginToken.class);
			if (token == null) {
				logger_auth_tracker.info("[token] empty=" + authCode);
			} else {
				return token;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
