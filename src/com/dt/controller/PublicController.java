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

import com.dt.entity.DetectorFacility;
import com.dt.entity.DetectorTriggerLog;
import com.dt.entity.EquipmentInfo;
import com.dt.service.BaseInfoService;
import com.dt.service.PublicService;
import com.dt.util.Pager;
import com.dt.util.StringUtil;

@Controller
@RequestMapping(value = "/publicController")
public class PublicController {

	
	@Autowired
	private PublicService publicService;

	@RequestMapping(value = "selectFacilityList.do")
	public @ResponseBody String selectFacilityList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject jsonObject = new JSONObject();
		List<DetectorFacility> list=publicService.selectFacilityList();
		jsonObject.put("list", list);
		return jsonObject.toString();
	}

}
