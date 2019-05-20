package com.yzl.ssm.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用的状态信息
 * @author Administrator
 *
 */
public class Msg {
	//100 成功  200 失败
	private int code;
	//提示信息
	private String msg;
	//用户要返回给浏览器的数据
	private Map<String,Object> map = new HashMap<String,Object>();

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
	public static Msg success() {
		Msg result = new Msg();
		result.setCode(100);
		result.setMsg("处理请求成功");
		
		return result;
		
	}
	
	public static Msg fail() {
		Msg result = new Msg();
		result.setCode(200);
		result.setMsg("处理请求失败");
		
		return result;
		
	}
	
	public Msg add(String key,Object value)
	{
		this.getMap().put(key, value);
		
		return this;
	}
	
}
