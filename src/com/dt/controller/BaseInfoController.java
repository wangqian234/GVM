package com.dt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;

import com.dt.entity.DetectorTriggerLog;
import com.dt.entity.EquipmentInfo;
import com.dt.service.BaseInfoService;
import com.dt.util.Pager;
import com.dt.util.StringUtil;

@Controller
@RequestMapping(value = "/baseInfoController")
public class BaseInfoController {

	@Autowired
	private BaseInfoService baseInfoService;

	@RequestMapping(value = "/selectBaseList.do")
	public @ResponseBody String selectBaseList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject jsonObject = JSONObject.fromObject(request.getParameter("limit"));
		String project = "";
		String facility = "";
		if (jsonObject.containsKey("project")) {
			if (StringUtil.strIsNotEmpty(jsonObject.getString("project"))) {
				project = jsonObject.getString("project").toString();
			}
		}
		if (jsonObject.containsKey("facility")) {
			if (StringUtil.strIsNotEmpty(jsonObject.getString("facility"))) {
				facility = jsonObject.getString("facility").toString();
			}
		}
		List<EquipmentInfo> list = null;
		Pager pager = new Pager();
		if (!project.equals("") && !facility.equals("")) {
			List<Object> totalRow = baseInfoService.getbaseTotalRow(project, facility);
			pager.setPage(Integer.parseInt(request.getParameter("page")));// 指定页码
			pager.setTotalRow(Integer.parseInt(totalRow.get(0).toString()));
			list = baseInfoService.findBaseList(project, facility,pager.getOffset(), pager.getLimit());
		}
		JSONObject jo = new JSONObject();
		jo.put("list", list);
		jo.put("totalPage", pager.getTotalPage());
		System.out.println(list.size());
		System.out.println(jo.toString());
		return jo.toString();
	}
	
	
	@RequestMapping(value = "/selectEquipmentById.do")
	public @ResponseBody String selectEquipmentById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject jsonObject = new JSONObject();
		String equipmentId =request.getParameter("equipmentId");
		EquipmentInfo equipmentInfo=new EquipmentInfo();
		equipmentInfo=baseInfoService.selectEquipmentById(Integer.parseInt(equipmentId));
		jsonObject.put("equipmentInfo", equipmentInfo);
		return jsonObject.toString();
	}


}
