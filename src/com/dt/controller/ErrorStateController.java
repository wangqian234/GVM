package com.dt.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.util.TempFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.entity.DetectorTriggerLog;
import com.dt.service.ErrorStateService;
import com.dt.util.Pager;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/errorState")
public class ErrorStateController {

	@Autowired
	ErrorStateService errorStateService;
	
	@RequestMapping(value="/selectErrorList.do")
	public @ResponseBody String selectErrorList(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		JSONObject jsonObject = JSONObject.fromObject(request.getParameter("limit"));
		String state = "";
		String startDate = "";
		String endDate = "";
		if(request.getParameter("state") != null){
			state = request.getParameter("state");
		}
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
		List totalRow = errorStateService.getErrorTotalRow(startDate, endDate, state);
		Pager pager = new Pager();
		pager.setPage(Integer.parseInt(request.getParameter("page")));// 指定页码
		pager.setTotalRow(Integer.parseInt(totalRow.get(0).toString()));
		
		List<DetectorTriggerLog> list = errorStateService.findErrorList(startDate, endDate, pager.getOffset(), pager.getLimit(), state);
		JSONObject json = new JSONObject();
		json.put("totalPage", pager.getTotalPage());
		json.put("list", list);
		System.out.println(json);
		return json.toString();
	}
	
//	@RequestMapping(value="/analyseError.do")
//	public @ResponseBody String analyseError(HttpServletRequest request, HttpServletResponse response, HttpSession session){
//		JSONObject jsonObject = JSONObject.fromObject(request.getParameter("limit"));
//		String startDate = "";
//		String endDate = "";
//		if (jsonObject.containsKey("startTime")) {
//			if (strIsNotEmpty(jsonObject.getString("startTime"))) {
//				startDate = dayFirstTime(jsonObject.getString("startTime"));
//			}
//		}
//		if (jsonObject.containsKey("endTime")) {
//			if (strIsNotEmpty(jsonObject.getString("endTime"))) {
//				endDate = dayLastTime(jsonObject.getString("endTime"));
//			}
//		}
//		
//		//List<List<Map<String, String>>> listPie = errorStateService.analyseErrorPie(startDate, endDate);
//		//List<Map<String, String>> listLine = errorStateService.analyseErrorLine(startDate, endDate);
//		JSONObject json = new JSONObject();
//		json.put("listPie", "");
//		json.put("listLine", "");
//		return json.toString();
//	}
	
	@RequestMapping(value="/selectErrorDetails.do")
	public @ResponseBody String selectErrorDetails(HttpServletRequest request, HttpServletResponse response){
		String SensorId = request.getParameter("SensorId");
		Map<String, String> map = errorStateService.selectErrorDetails(SensorId);
		JSONObject json = new JSONObject();
		json.put("list", map);
		return json.toString();
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
	
	/**
	 *
	 * 
	 * @param zq查询报警个数
	 * @return Integer num
	 */
	@RequestMapping(value="/selectErrorNum.do")
	public @ResponseBody String selectErrorNum(HttpServletRequest request, HttpServletResponse response){
		
		JSONObject jsonObject = new JSONObject();
		/*int alertTotalNum = errorStateService.getErrorTotalRow(0);
		jsonObject.put("alertTotalNum", alertTotalNum);*/
		return jsonObject.toString();
	}
}
