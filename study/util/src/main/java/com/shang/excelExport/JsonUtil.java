package com.shang.excelExport;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/** 
 * JSON转换工具类 
 * @author NICK 
 * @date 2015-10-13 
 */  
public class JsonUtil {

	/** 
     * 对象转换成JSON字符串 
     *  
     * @param obj 
     *            需要转换的对象 
     * @return 对象的string字符 
     */  
    public static String toJson(Object obj) {  
    	String result = null;
		ObjectMapper mapper = new ObjectMapper();  
		try {
			result = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
    }  
  
    /** 
     * JSON字符串转换成对象 
     *  
     * @param jsonString 
     *            需要转换的字符串 
     * @param type 
     *            需要转换的对象类型 
     * @return 对象 
     */  
    @SuppressWarnings("unchecked")  
    public static <T> T fromJson(String jsonString, Class<T> type) {  
    	
    	ObjectMapper mapper = new ObjectMapper();  
  
    	try {
    		return  mapper.readValue(jsonString, type);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	 
		return null;
    }  
    
  
//    /** 
//     * 将JSONArray对象转换成list集合 
//     *  
//     * @param jsonArr 
//     * @return 
//     */  
//    public static List<Object> jsonToList(JSONArray jsonArr) {  
//    	return null;
//    }  
  
    /** 
     * 将json字符串转换成map对象 
     *  
     * @param json 
     * @return 
     */  
    public static Map<String, Object> jsonToMap(String json) {  
    	return null;
    }  
  
//    /** 
//     * 将JSONObject转换成map对象 
//     *  
//     * @param json 
//     * @return 
//     */  
//    public static Map<String, Object> jsonToMap(JSONObject obj) {  
//    	return null; 
//    }  
    
}
