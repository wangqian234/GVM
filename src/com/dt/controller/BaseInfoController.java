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

import com.alibaba.fastjson.JSONObject;
import com.dt.entity.EquipmentInfo;
import com.dt.service.BaseInfoService;

@Controller
@RequestMapping(value = "/baseInfoController")
public class BaseInfoController {

	@Autowired
	private BaseInfoService baseInfoService;

	@RequestMapping(value = "/selectBaseList.do")
	public @ResponseBody String selectBaseList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		JSONObject jsonobject = new JSONObject();
		List<EquipmentInfo> list = null;
		list = baseInfoService.selectBaseList();
		System.out.println("fsdfsdfs");
		jsonobject.put("list", list);
		jsonobject.put("list1", "占股去年");

		System.out.println(jsonobject.toString());
		return jsonobject.toString();
	}

}
