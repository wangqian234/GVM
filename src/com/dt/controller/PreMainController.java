package com.dt.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.dt.entity.PreInfo;
import com.dt.service.PreMainService;

@Controller
@RequestMapping("/preMainController")
public class PreMainController {

	@Autowired
	private PreMainService preMainService;
	
	//设备list
	@SuppressWarnings("unused")
	@RequestMapping(value = "/selectEquipList.do")
	public @ResponseBody String selectEquipList (HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String project = request.getParameter("project");
		String facility = request.getParameter("facility");
		String name = request.getParameter("name");
		JSONObject jsonObject = new JSONObject();
		List<PreInfo> list = null;
	    list = preMainService.selectEquipList();
		System.out.println("fsdfsdfs");
		jsonObject.put("list", list);
		System.out.println(jsonObject.toString());
		return jsonObject.toString();
	}
	
	//分析list
	@RequestMapping("/analyzeList.do")
	public @ResponseBody String analyzeList (HttpServletRequest request , HttpServletResponse response) throws ParseException {
		String name = request.getParameter("name");
		JSONObject jsonObject = new JSONObject();
		Integer preId = Integer.valueOf(request.getParameter("preId"));
		String list = null;
		list = preMainService.analyzeList(preId, null);
		System.out.println("分析");
		jsonObject.put("list", list);
		System.out.println(jsonObject.toString());
		return jsonObject.toString();
	}
}
