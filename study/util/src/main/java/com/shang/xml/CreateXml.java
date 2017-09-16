package com.shang.xml;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

public class CreateXml {

	

	  
	public  static void  CreateXml () throws ParserConfigurationException{   

	  //     
	  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();   
	  //    
	  DocumentBuilder db = dbf.newDocumentBuilder();   
	  Document doc = db.newDocument();   
	  // 创建根元素 root    
	  Element root = doc.createElement("Rate");   
	  // 创建子元素 name    
	  Element item = doc.createElement("caliberid");   
	  // 创建子元素的文本    
	  item.appendChild(doc.createTextNode("00"));   
	  
 
	  
	  // 为根元素添加节点    
	  root.appendChild(item);   
	  // 创建age元素    
	  item = doc.createElement("age");   
	  item.appendChild(doc.createTextNode("28"));   
	  // 为根元素添加age节点    
	  root.appendChild(item);   
	  // high    
	  item = doc.createElement("high");   
	  item.appendChild(doc.createTextNode("180cm"));   
	  root.appendChild(item);   
	     
	  item = doc.createElement("score");   
	  // 创建score的子元素lessons    
	  Element lessons = doc.createElement("lessons");   
	  // 创建lessons的子元素lesson    
	  Element lesson = doc.createElement("java");   
	  // 为子元素添加文本    
	  lesson.appendChild(doc.createTextNode("90"));   
	  // 把子元素添加到父节点上    
	  lessons.appendChild(lesson);   
	  // 把子元素添加到父节点上    
	  item.appendChild(lessons);   
	     
	  lessons = doc.createElement("lessons");   
	  lesson = doc.createElement("struts");   
	  lesson.appendChild(doc.createTextNode("80"));   
	  lessons.appendChild(lesson);   
	  item.appendChild(lessons);   
	     
	  lessons = doc.createElement("lessons");   
	  lesson = doc.createElement("hibernet");   
	  lesson.appendChild(doc.createTextNode("100"));   
	  lessons.appendChild(lesson);   
	  item.appendChild(lessons);   
	     
	  root.appendChild(item);   
	  // 为文档添加根节点    
	  doc.appendChild(root);   
	  //  指定输出格式    
	  OutputFormat format = new OutputFormat(doc, "utf-8", true);   
	  StringWriter stringOut = new StringWriter();   
	  XMLSerializer serial = new XMLSerializer(stringOut,format);   
	  // 将DOM数转化成字符串    
	  try {   
	   serial.asDOMSerializer();   
	   serial.serialize(doc.getDocumentElement());   
	   // 创建文件输出流    
	   PrintStream ps = new PrintStream(new FileOutputStream("d:\\new.xml"));   
	   // 输出ｘｍｌ    
	   ps.println(stringOut.toString());   
	  } catch (IOException e) {   
	   e.printStackTrace();   
	  }    
	}   
	
	public static void main(String[] args) {
		CreateXml xml = new CreateXml();
		
		try{
			xml.CreateXml();
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
}
