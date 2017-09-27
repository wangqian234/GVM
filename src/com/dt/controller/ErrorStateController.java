package com.dt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.service.ErrorStateService;
import com.dt.util.Pager;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/errorState")
public class ErrorStateController {

	@Autowired
	ErrorStateService errorStateService;
	
	@RequestMapping("/selectErrorList")
	public @ResponseBody String selectErrorList(HttpServletRequest request, HttpSession session){
		JSONObject jsonObject = JSONObject.fromObject(request.getParameter("limit"));
		String startDate = "";
		String endDate = "";
		if (jsonObject.containsKey("startTime")) {
			if (strIsNotEmpty(jsonObject.getString("startTime"))) {
				startDate = dayFirstTime(jsonObject.getString("startTime"));
			}
		}
		if (jsonObject.containsKey("endTime")) {
			if (strIsNotEmpty(jsonObject.getString("endTime"))) {
				endDate = dayLastTime(jsonObject.getString("endTime"));
			}
		}
		List totalRow = errorStateService.getErrorTotalRow(startDate, endDate);
		System.out.println(totalRow);
		Pager pager = new Pager();
		pager.setPage(Integer.parseInt(request.getParameter("page")));// 指定页码
		pager.setTotalRow(Integer.parseInt(totalRow.get(0).toString()));
		
		jsonObject.put("totalRow", totalRow.get(0).toString());
		jsonObject.put("list", "list");
		return jsonObject.toString();
	}
	
	public  Boolean strIsNotEmpty(String s) {
		Boolean flag = true;
		if (s == null || s.length() <= 0) {
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 当天0时刻
	 * 
	 * @param str
	 * @return yyyy-MM-dd 00:00:00
	 */
	public String dayFirstTime(String str) {
		return str + " 00:00:00";
	}

	/**
	 * 当天最后时间
	 * 
	 * @param str
	 * @return yyyy-MM-dd 23:59:59
	 */
	public String dayLastTime(String str) {
		return str + " 23:59:59";
	}
}
