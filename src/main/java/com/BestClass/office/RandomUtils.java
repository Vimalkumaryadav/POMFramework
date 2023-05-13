package com.BestClass.office;

import java.io.File;
import java.lang.reflect.Type;
import java.security.SecureRandom;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class RandomUtils {

	private static final SecureRandom random = new SecureRandom();
	
	public static String generateRandomString(int length) {
		String text = "AaBbCc123415@#$%";
		StringBuilder sb = new StringBuilder(length);
		for(int i=0;i<length;i++) {
			sb.append(text.charAt(random.nextInt(text.length())));
		}
		return sb.toString();
	}
	
	public static String generateRandomNumericString(int length) {
		String text = "0123456789";
		StringBuilder sb = new StringBuilder(length);
		for(int i=0;i<length;i++) {
			sb.append(text.charAt(random.nextInt(text.length())));
		}
		return sb.toString();
	}
	
	public static void CreateFolder(String path) {
		File file = new File(path);
		if(!file.exists()) {
			file.mkdir();
		}
	}
	
	public static String TimeStamp() {
		Date now = new Date();
		return now.toString().replace(":", "-");
	}
	
	public static <T>T copyObject(Object object){
		Gson gson = new Gson();
		JsonObject jsonObject = gson.toJsonTree(object).getAsJsonObject();
		return gson.fromJson(jsonObject, (Type) object.getClass());
	}
	
}
