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

import com.dt.entity.DetectorEquipment;
import com.dt.service.EvalStateService;

import net.sf.json.JSONObject;

/**
 * 设备健康状态评估
 * @author ghl
 * @date   2017年9月26日
 */
@Controller
@RequestMapping("/evalStateController")
public class EvalStateController {
	@Autowired
	EvalStateService evalStateService;
	/**
	 * 查询设备
	 * @param request
	 * @param response
	 * @param session
	 * @return list
	 */
	@RequestMapping("/selectEquipment.do")
	public @ResponseBody String selectEquipment(HttpServletRequest request, HttpServletResponse  response)
			throws ServletException, IOException{
		Integer Detector_Facility_Id = Integer.parseInt(request.getParameter("Detector_Facility_Id"));
		List<DetectorEquipment> list = evalStateService.selectEquipment(Detector_Facility_Id);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", list);
		return jsonObject.toString();
	}
	/**
	 * 查询分析设备数据
	 * @param request
	 * @param response
	 * @param session
	 * @return list
	 */
	@RequestMapping("/analysisEquipment.do")
	public @ResponseBody String analysisEquipment(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		Integer Detector_Equipment_Id = Integer.parseInt(request.getParameter("detector_Equipment_Id"));
	    String Failare= evalStateService.findEquipment(Detector_Equipment_Id);
		String listInfo  = evalStateService.analysisEquipment(Detector_Equipment_Id,Failare);	
		return listInfo;
	}
}
