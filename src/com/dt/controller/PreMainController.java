package com.dt.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.entity.PreInfo;
import com.dt.service.PreMainService;
import com.dt.util.Pager;
import com.dt.util.StringUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/preMainController")
public class PreMainController {

	@Autowired
	private PreMainService preMainService;
	
	//设备基本信息
	@RequestMapping(value = "/selectEqInfo.do")
	public @ResponseBody String selectEqInfo(HttpServletRequest request, HttpServletResponse response){
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
		List<PreInfo> list = null;
		Pager pager = new Pager();
		if (!project.equals("") && !facility.equals("")) {
			List<Object> totalRow = preMainService.selectEqTotalRow(project, facility);
			pager.setPage(Integer.parseInt(request.getParameter("page")));
			pager.setTotalRow(Integer.parseInt(totalRow.get(0).toString()));
			if(project != null && facility != null){
				list = preMainService.selectEqInfo(project, facility,pager.getOffset(), pager.getLimit());
			}
		}
		JSONObject object = new JSONObject();
		object.put("list", list);
		object.put("totalPage", pager.getTotalPage());
		return object.toString();
	}
	
	//设备预测信息
	@RequestMapping(value = "/selectEquipDateById.do")
	public @ResponseBody String selectEquipDateById(HttpServletRequest request, HttpServletResponse response) throws ParseException{
		JSONObject jsonObject = new JSONObject();
		String equipmentId = request.getParameter("equipmentId");
		String equipmentTypeId = request.getParameter("equipmentTypeId");
		PreInfo preInfo = new PreInfo();
		if(!equipmentId.equals("") && !equipmentTypeId.equals("")){
			preInfo = preMainService.selectEquipDateById(Integer.parseInt(equipmentId),Integer.parseInt(equipmentTypeId));//null
		}
		jsonObject.put("preInfo", preInfo);
		return jsonObject.toString();
	}
	
	
}
