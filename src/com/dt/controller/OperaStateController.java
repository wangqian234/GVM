package com.dt.controller;

import java.util.List;

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
		List<Object> listObject = operaStateService.getbaseInfo(project, facility);
		System.out.println(listObject);
//		
//		//首先判断Detector_Sensor_Type是否为状态值:1为运行状态信息、2为报警信息
//		for(int i = 0 ; i < list.size() ; i++) {
//			if(list.get(i).get("Detector_Sensor_Type") == "2"){
//				listmap.add(list.get(i));
//				list.remove(i);
//			};
//		}
//		jsonObject.put("alert", listmap);
//		if(listmap.size() == 0 && list.size() == 0){
//			jsonObject.put("size", 0);
//		}
//
//		jsonObject.put("operation", list);
//		System.out.println(jsonObject.toString());
		return listObject.toString();
	}
}
