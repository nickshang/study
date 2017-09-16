package com.shang.generic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 
 * @类描述：常用容器对象生成工具类，杠杠利用类型参数推断，
 * 避免在编写创建容量时，重复的参数泛型参数列表
 * 
 * 备注：JDK1.8编译器已经不存在这个限制。
 * 
 * Map<Person,List<? extends Pet>> petPeople = new 
 * 		new HashMap<Person,List<? exntends Pet>>();
 * 
 * 替换为：
 * Map<Person,List<? extends Pet>> petPeople = 
 * New.map();
 * 
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
 */
public final  class New {
	
	public final static <K,V> Map<K,V> map(){
		return new HashMap<K, V>();
	}
	
	public final static <T> List<T> list(){
		return new ArrayList<T>();
	}
	
	
	public final static <T> Set<T> set(){
		return new HashSet<T>();
	}
	
	public final static <T> Queue<T> queue(){
		return new LinkedList<T>();
	}
	
	public static void main(String[] args) {
		Map<String,String> map = new HashMap<>();
		map.put("11", "22");
		System.out.println(map.get("11"));
	}
}
