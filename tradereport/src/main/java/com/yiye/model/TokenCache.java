package com.yiye.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSON;

/**
 * seller -> token
 * 
 * @author tony
 * 
 */
public class TokenCache {

	private ConcurrentHashMap<String, LoginToken> map;
	private static TokenCache instance = null;
	
	private TokenCache() {
	}
	
	public static TokenCache getInstance() {
		if (instance == null) {
			synchronized (TokenCache.class) {
				if (null == instance) {
					instance = new TokenCache();
					instance.map = new ConcurrentHashMap<String, LoginToken>();
				}
			}
		}
		return instance;
	}
	
	public void load(String fileName) {
		try {
			File file = new File(fileName);
			if (file.exists()) {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String str = null;
				while ((str = reader.readLine()) != null) {
					String[] arr = str.split("=");
					LoginToken token = JSON.parseObject(arr[1], LoginToken.class);
					map.put(arr[0], token);
				}
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将保存list的临时文件载入内存
	 */
	public void load() {
		load(Config.TOKEN_DUMPFILE);
	}
	
	/**
	 * 将目前内存中的map写入临时文件
	 */
	public void dump() {
		try {
			File file = new File(Config.TOKEN_DUMPFILE);
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
			Iterator<Map.Entry<String, LoginToken>> ite = map.entrySet().iterator();
			while (ite.hasNext()) {
				Map.Entry<String, LoginToken> entry = ite.next();
				String key = entry.getKey();
				LoginToken value = entry.getValue();
				String line = key + "=" + JSON.toJSONString(value) + "\n";
				writer.write(line);
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ConcurrentHashMap<String, LoginToken> map() {
		return map;
	}
	
	public LoginToken get(String seller) {
		return map.get(seller);
	}

	public void put(String seller, LoginToken token) {
		map.put(seller, token);
	}
	
}