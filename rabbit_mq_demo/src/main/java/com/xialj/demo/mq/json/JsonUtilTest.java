package com.xialj.demo.mq.json;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import com.alibaba.fastjson.JSONObject;

public class JsonUtilTest {
	public static void main(String[] args) {
		JSONObject json = new JSONObject();
		json.put("keyA", 1);
		json.put("keyB", "this is String");
		byte[] ba = "this is String".getBytes();
		System.out.println(ba);
		System.out.println(Arrays.toString(ba));
		json.put("keyC", ba);
		json.put("keyD", Arrays.toString(ba));
		System.out.println(json.toJSONString());
		System.out.println(json.get("keyC"));
		System.out.println(json.getJSONArray("keyC").size());
		System.out.println(json.get("keyD"));
		System.out.println(json.getJSONArray("keyD").size());
		System.out.println(json.getJSONArray("keyC"));
		byte[] ba2 = json.getBytes("keyC");		
		System.out.println( Arrays.toString(ba2));
		byte[] ba3 = json.getBytes("keyD");		
		System.out.println( Arrays.toString(ba3));
	}
}
