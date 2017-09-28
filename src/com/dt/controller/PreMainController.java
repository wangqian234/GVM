package com.dt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.dt.entity.DetectorEquipment;
import com.dt.entity.EquipmentInfo;
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
		String name = request.getParameter("name");
		List<EquipmentInfo> list = preMainService.selectEquipList();
		System.out.println("chengjiang_yu");
		jsonObject.put("list", list);
		jsonObject.put("list1", "chengjiang_yu");
		System.out.println(jsonObject.toString());
		return jsonObject.toString();
	}
	
	//分析list
	@RequestMapping("/analyzeList.do")
	public @ResponseBody List<DetectorEquipment> analyzeList (HttpServletRequest request , HttpServletResponse response){
		Integer preid = Integer.valueOf(request.getParameter("equipId"));
		List<DetectorEquipment> result = preMainService.analyzeList(preid);
		return result;
	}
	
}
