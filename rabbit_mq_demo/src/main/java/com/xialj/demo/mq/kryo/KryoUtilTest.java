package com.xialj.demo.mq.kryo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Base64;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.JavaSerializer;

public class KryoUtilTest {
	private static String[] nameSeq = { "笔记本", "鼠标", "内存", "硬盘", "机箱" };
	private static BigDecimal[] priceReq = { new BigDecimal("9889"), new BigDecimal("98"), new BigDecimal("889"),
			new BigDecimal("589"), new BigDecimal("89") };

	public static <T> byte[] serializationObject(Class<T> clazz, Object obj) throws FileNotFoundException {
		Kryo kryo = new Kryo();
		kryo.setReferences(false);
		kryo.setRegistrationRequired(false);
		kryo.register(clazz, new JavaSerializer());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Output output = new Output(baos);
		kryo.writeClassAndObject(output, obj);
		output.flush();
		output.close();
		byte[] b = baos.toByteArray();
		try {
			baos.flush();
			baos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return b;
	}

	public static <T> Object deserializationObject(byte[] byteData,Class<T> clazz) {
		Kryo kryo = new Kryo();
		kryo.setReferences(false);
		kryo.register(clazz, new JavaSerializer());
		ByteArrayInputStream bais = new ByteArrayInputStream(byteData);
		Input input = new Input(bais);
		return kryo.readClassAndObject(input);
	}
}
