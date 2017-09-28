package com.dt.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.service.OperaStateService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/operaState")
public class OperaStateController {
	
	@Autowired
	OperaStateService operaStateService;

	/**
	 * 获取设备运行状态信息
	 * request：project、facility
	 * return：设备状态、设备信息
	 * 
	 * */
	@RequestMapping("/getOperaState.do")
	public @ResponseBody String getBaseInfo(HttpServletRequest request, HttpServletResponse response){
		String project = request.getParameter("project");
		String facility = request.getParameter("facility");
		JSONObject jsonObject = new JSONObject();
		List<Map<String, String>> listObject = operaStateService.getbaseInfo(project, facility);
		jsonObject.put("list", listObject);
		System.out.println(jsonObject.toString());
		return jsonObject.toString();
	}
}
