package com.xialj.demo.mq.bson;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import de.undercouch.bson4jackson.BsonFactory;

public class ManualSample {
	public static void main(String[] args) throws Exception {
		// create dummy POJO
		Person bob = new Person();
		bob.setName("Bob");

		// create factory
		BsonFactory factory = new BsonFactory();

		// serialize data
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JsonGenerator gen = factory.createJsonGenerator(baos);
		gen.writeStartObject();
		gen.writeFieldName("name");
		gen.writeString(bob.getName());
		gen.close();
		System.out.println(bob.getName());		
		System.out.println(baos.toByteArray());
		String bosnStr = new String(baos.toByteArray(),"UTF-8");
		System.out.println(bosnStr);
		// deserialize data
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());		
		System.out.println(clonePersonbyBson(factory,bais).getName());
		ByteArrayInputStream bais2 = new ByteArrayInputStream(bosnStr.getBytes());
		System.out.println(clonePersonbyBson(factory,bais2).getName());
	}
	
	private static Person clonePersonbyBson(BsonFactory factory,ByteArrayInputStream bais) throws IOException {
		JsonParser parser = factory.createJsonParser(bais);
		Person clone_of_bob = new Person();
		parser.nextToken();
		while (parser.nextToken() != JsonToken.END_OBJECT) {
			String fieldname = parser.getCurrentName();
			parser.nextToken();
			if ("name".equals(fieldname)) {
				clone_of_bob.setName(parser.getText());
			}
		}
		return clone_of_bob;
	}
}
