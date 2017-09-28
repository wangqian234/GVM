package com.dt.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.dt.service.PreMainService;

@Controller
@RequestMapping("/preMainController")
public class PreMainController {

	@Autowired
	private PreMainService preMainService;
	
	//设备list
	@RequestMapping(value = "/selectEquipList.do")
	public @ResponseBody String selectEquipList (HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		JSONObject jsonObject = new JSONObject();
		List<Map<String,String>> list = preMainService.selectEquipList();
		System.out.println("chengjiang_yu");
		jsonObject.put("list", list);
		System.out.println(jsonObject.toString());
		return jsonObject.toString();
	}
	
	//分析list
	@RequestMapping("/analyzeList.do")
	public @ResponseBody String analyzeList (HttpServletRequest request , HttpServletResponse response) throws ParseException{
		JSONObject jsonObject = new JSONObject();
		List<Map<String,String>> list = preMainService.analyzeList();
		jsonObject.put("list", list);
		System.out.println(jsonObject.toString());
		return jsonObject.toString();
	}
}
