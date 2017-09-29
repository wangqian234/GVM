package com.dt.controller;

import java.awt.print.Pageable;
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
import com.dt.util.Pager;
import com.dt.util.StringUtil;

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
		JSONObject jsonObject = JSONObject.fromObject(request.getParameter("limit"));
		String project = "";
		String facility = "";
		if(jsonObject.containsKey("project")){
			if(StringUtil.strIsNotEmpty(jsonObject.getString("project"))){
				project = jsonObject.getString("project").toString();
			}
		}
		if(jsonObject.containsKey("facility")){
			if(StringUtil.strIsNotEmpty(jsonObject.getString("facility"))){
				facility = jsonObject.getString("facility").toString();
			}
		}
		List<DetectorEquipment> list = null;
		Pager pager = new Pager();
		if(!project.equals("") && !facility.equals("")){
			List<Object> totalRow = evalStateService.getbaseTotalRow(project,facility);
			pager.setPage(Integer.parseInt(request.getParameter("page")));//指定页码
			pager.setTotalRow(Integer.parseInt(totalRow.get(0).toString()));
			 list = evalStateService.selectEquipment(project,facility,pager.getOffset(),pager.getLimit());
		}
		JSONObject jsonO = new JSONObject();
		jsonO.put("list", list);
		jsonO.put("totalPage", pager.getTotalPage());
		return jsonO.toString();
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
		System.out.println("ss"+listInfo);
		return listInfo;
	}
}
