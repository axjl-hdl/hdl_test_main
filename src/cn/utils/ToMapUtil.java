package cn.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


public class ToMapUtil {
	
	//xml转map
	public static Map<String, Object> getMapByXml(String xml){
		Map<String , Object> map = new HashMap<String, Object>();
		try {
			Document doc = DocumentHelper.parseText(xml);
			Element rootElement = doc.getRootElement();
			Map<String, Object> map2=new HashMap<String, Object>();
			map = ele2map(map2,rootElement);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public static Map<String , Object> ele2map(Map<String, Object> map,Element ele){
		List<Element> list = ele.elements();
		if (list.size()!=0){
			for (Element element : list) {
				if (element.elements().size()!=0) {
					List<Map<String, Object>> eleList = new ArrayList<Map<String,Object>>();
					eleList.add(ele2map(element));
					map.put(element.getName(), eleList);
				}else{
					map.put(element.getName(), element.getData());
				}
			}
		}
		return map;
	}
	
	public static Map<String , Object> ele2map(Element ele){
		Map<String, Object> map = new HashMap<String, Object>();
		List<Element> list = ele.elements();
		if (list.size()!=0){
			for (Element element : list) {
				if (element.elements().size()!=0) {
					map = ele2map(element);
				}else{
					map.put(element.getName(), element.getData());
				}
			}
		}
		return map;
	}
	
	//键值对转map
	public static Map<String, Object> getMapByKeyValue(String params){
		String[] list = params.split("&");
		Map<String,Object> map=new HashMap<String, Object>();
		for (String str : list) {
			String[] arr = (str+" ").split("=");
			map.put(arr[0], arr[1].toString().trim());
		}
		return map;
	}
	
	//json转map
	public static Map<String, Object> getMapByJson(String jsonParms){
		JSONObject object = JSONObject.fromObject(jsonParms);
		Map<String,Object> map = object;
		return map;
	}
	
}
