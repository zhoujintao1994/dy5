//package com.dayuan.dto;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.alibaba.fastjson.JSON;
//
//public class TestJson {
//
//	public static void main(String[] args) {
//		AjaxDTO dto = new AjaxDTO(true, "大猿IT");
//		String jsonString = JSON.toJSONString(dto);
//		System.out.println(jsonString);
//		
//		String xxx = "{\"message\":\"大猿IT\",\"success\":true}";
//		AjaxDTO dto2 = JSON.parseObject(xxx, AjaxDTO.class);
//		System.out.println(dto2.getMessage());
//		
//		List<AjaxDTO> list = new ArrayList<>();
//		list.add(new AjaxDTO(true, "大猿IT"));
//		list.add(new AjaxDTO(true, "大猿IT"));
//		
//		
//		System.out.println(JSON.toJSONString(list));
//		
//	}
//}
