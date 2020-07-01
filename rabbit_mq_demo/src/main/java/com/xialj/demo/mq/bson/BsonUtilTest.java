package com.xialj.demo.mq.bson;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.undercouch.bson4jackson.BsonFactory;

public class BsonUtilTest {

	public static byte[] serializationObjectbyObjectMapper(Object obj) throws Exception {
		// serialize data
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectMapper mapper = new ObjectMapper(new BsonFactory());
		mapper.writeValue(baos, obj);
		return baos.toByteArray();
	}
	
	public static <T> T  deserializationObject(byte[] bytearray,Class<T> clazz) throws JsonParseException, JsonMappingException, IOException{
		ByteArrayInputStream bais = new ByteArrayInputStream(bytearray);
		ObjectMapper mapper = new ObjectMapper(new BsonFactory());		
		return mapper.readValue(bais, clazz);	
	}
}
