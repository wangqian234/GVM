package com.dt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.dt.entity.EquipmentInfo;
import com.dt.service.PreMainService;

@Controller
@RequestMapping("/preMainController")
public class PreMainController {

	@Autowired
	private PreMainService preMainService;
	
	@RequestMapping("/selectEquipList")
	public @ResponseBody String selectEquipList (HttpServletRequest request,HttpServletResponse response){
		JSONObject jsonObject = new JSONObject();
		List<EquipmentInfo> list = preMainService.selectEquipList();
		System.out.println("chengjiang_yu");
		jsonObject.put("list", list);
		jsonObject.put("list1", "chengjiang_yu");
		
		System.out.println(jsonObject.toString());
		return jsonObject.toString();
	}
}
