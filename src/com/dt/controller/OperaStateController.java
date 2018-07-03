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
import com.dt.util.Pager;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/operaState")
public class OperaStateController {

	@Autowired
	OperaStateService operaStateService;

	/**
	 * 获取设备运行状态信息 request：project、facility return：设备状态、设备信息
	 * 
	 */
	@RequestMapping("/getOperaState.do")
	public @ResponseBody String getBaseInfo(HttpServletRequest request, HttpServletResponse response) {
		String project = request.getParameter("project");
		String facility = request.getParameter("facility");
		String listObject = operaStateService.getbaseInfo(project, facility);
		return listObject;
	}

	@RequestMapping("/getOperaDetails.do")
	public @ResponseBody String getOperaDetails(HttpServletRequest request, HttpServletResponse response) {
		String sensorId = request.getParameter("sensorId");
		List<Map<String, String>> list = operaStateService.getOperaDetails(sensorId);
		JSONObject obj = new JSONObject();
		obj.put("list", list);
		return obj.toString();
	}
}
