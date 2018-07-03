package com.dt.controller;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;

import com.dt.entity.DetectorFacility;
import com.dt.entity.EquipmentInfo;
import com.dt.entity.PreInfo;
import com.dt.service.BaseInfoService;
import com.dt.service.ErrorStateService;
import com.dt.service.EvalStateService;
import com.dt.service.PreMainService;
import com.dt.service.PublicService;
import com.dt.util.StringUtil;

@Controller
@RequestMapping(value = "/publicController")
public class PublicController {

	@Autowired
	private PublicService publicService;
	@Autowired
	ErrorStateService errorStateService;
	@Autowired
	EvalStateService evalStateService;
	@Autowired
	private BaseInfoService baseInfoService;
	@Autowired
	private PreMainService preMainService;

	@RequestMapping(value = "selectFacilityList.do")
	public @ResponseBody String selectFacilityList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject jsonObject = new JSONObject();
		List<DetectorFacility> list = publicService.selectFacilityList();
		jsonObject.put("list", list);
		return jsonObject.toString();
	}

	@RequestMapping(value = "selectErrorsByMode.do")
	public @ResponseBody String selectErrorsByMode(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject jsonObject = new JSONObject();
		List weixin = errorStateService.getErrorTotalRow("", "", "0", "1");
		List duanxin = errorStateService.getErrorTotalRow("", "", "0", "2");
		List youjian = errorStateService.getErrorTotalRow("", "", "0", "3");
		List pingtai = errorStateService.getErrorTotalRow("", "", "0", "5");
		/*
		 * Integer alarmNum = Integer.parseInt(weixin.get(0).toString()) +
		 * Integer.parseInt(duanxin.get(0).toString()) +
		 * Integer.parseInt(youjian.get(0).toString()) +
		 * Integer.parseInt(pingtai.get(0).toString());
		 */
		List yujing = errorStateService.getErrorTotalRow("", "", "0", "6");
		jsonObject.put("alarmNum1", weixin.get(0).toString());
		jsonObject.put("alarmNum2", duanxin.get(0).toString());
		jsonObject.put("alarmNum3", youjian.get(0).toString());
		jsonObject.put("alarmNum5", pingtai.get(0).toString());
		jsonObject.put("preAlarmNum", yujing.get(0).toString());
		return jsonObject.toString();
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "selectFaultList.do")
	public @ResponseBody String selectDaultList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject jsonObject = new JSONObject();
		List<EquipmentInfo> list = baseInfoService.selectBaseList("0", "0");
		for (int i = 0; i < list.size(); i++) {
			int eqId = list.get(i).getEquipment_Id();
			String failare = evalStateService.findEquipment(eqId);
			list.get(i).setEquipment_Fault(StringUtil.save2Float(Float.parseFloat(failare)));
		}
		Collections.sort(list, new PriceComparator());
		int num1 = 0;
		int num2 = 0;
		int num3 = 0;
		for (int i = 0; i < 8; i++) {
			String project = list.get(i).getEquipment_Project();
				if (project == "展会演示") {
					num1++;
				} else if (project == "清远凤城丽都") {
					num2++;
				} else if (project == "光明迈瑞") {
					num3++;
				}
		}
		
		String str1 = "";
		String str2 = "";
		String str3 = "";
		if (num1 >= num2 && num1 >= num3) {
			str1 = "展会演示 ";
			if (num2 >= num3) {
				str2 = "清远凤城丽都";
				str3 = "光明迈瑞";
			} else {
				str2 = "光明迈瑞";
				str3 = "清远凤城丽都";
			}
		}
		if (num2 > num1 && num2 > num3) {
			str1 = "清远凤城丽都 ";
			if (num1 >= num3) {
				str2 = "展会演示";
				str3 = "光明迈瑞";
			} else {
				str2 = "光明迈瑞";
				str3 = "展会演示";
			}
		}
		if (num3 > num1 && num3 > num2) {
			str1 = "光明迈瑞 ";
			if (num1 >= num2) {
				str2 = "展会演示";
				str3 = "清远凤城丽都";
			} else {
				str2 = "清远凤城丽都";
				str3 = "展会演示";
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(list.get(0).getEquipment_Project() + list.get(0).getEquipment_Name() + "、 ");
		sb.append(list.get(1).getEquipment_Project() + list.get(1).getEquipment_Name() + "、 ");
		sb.append(list.get(2).getEquipment_Project() + list.get(2).getEquipment_Name() + "。 ");
		jsonObject.put("list", list);
		jsonObject.put("result0", sb.toString());
		jsonObject.put("result1", str1);
		jsonObject.put("result2", str2);
		jsonObject.put("result3", str3);
		return jsonObject.toString();
	}
	// 自定义比较器：按书的价格排序
	static class PriceComparator implements Comparator {
		public int compare(Object object1, Object object2) {// 实现接口中的方法
			EquipmentInfo p1 = (EquipmentInfo) object1; // 强制转换
			EquipmentInfo p2 = (EquipmentInfo) object2;
			return new Double(p2.getEquipment_Fault()).compareTo(new Double(p1.getEquipment_Fault()));
		}
	}

	// preDateList
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "selectPreList.do")
	public @ResponseBody String findPreList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject jsonObject = new JSONObject();
		List<PreInfo> list = preMainService.selectEqInfo();
		for (int i = 1; i < list.size(); i++) {
			int equipmentId = list.get(i).getDetector_Equipment_Id();
			String preDate = preMainService.selectEquipDateById(equipmentId);
			list.get(i).setPreDate(preDate);
		}
		Collections.sort(list, new DateComparator());
		int num1 = 0;
		int num2 = 0;
		int num3 = 0;
		for (int i = 0; i < 8; i++) {
			String project = list.get(i).getProject();
				if (project == "展会演示") {
					num1++;
				} else if (project == "清远凤城丽都") {
					num2++;
				} else if (project == "光明迈瑞") {
					num3++;
				}
		}
		String str1 = "";
		String str2 = "";
		String str3 = "";
		if (num1 >= num2 && num1 >= num3) {
			str1 = "展会演示 ";
			if (num2 >= num3) {
				str2 = "清远凤城丽都";
				str3 = "光明迈瑞";
			} else {
				str2 = "光明迈瑞";
				str3 = "清远凤城丽都";
			}
		}
		if (num2 > num1 && num2 > num3) {
			str1 = "清远凤城丽都 ";
			if (num1 >= num3) {
				str2 = "展会演示";
				str3 = "光明迈瑞";
			} else {
				str2 = "光明迈瑞";
				str3 = "展会演示";
			}
		}
		if (num3 > num1 && num3 > num2) {
			str1 = "光明迈瑞 ";
			if (num1 >= num2) {
				str2 = "展会演示";
				str3 = "清远凤城丽都";
			} else {
				str2 = "清远凤城丽都";
				str3 = "展会演示";
			}
		}
		jsonObject.put("list", list);
		jsonObject.put("result1", str1);
		jsonObject.put("result2", str2);
		jsonObject.put("result3", str3);
		return jsonObject.toString();
	}

	// 自定义比较器：按书的价格排序
	static class DateComparator implements Comparator {
		public int compare(Object object1, Object object2) {// 实现接口中的方法
			PreInfo p1 = (PreInfo) object1; // 强制转换
			PreInfo p2 = (PreInfo) object2;
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date begin;
			Date end;
			int num = 1;
			try {
				if (p1.getPreDate() != null && p2.getPreDate() != null) {
					begin = (Date) dateFormat.parse(p1.getPreDate().toString().trim());
					end = (Date) dateFormat.parse(p2.getPreDate().toString().trim());

					if (begin.after(end)) {
						num = -1;
					} else {
						num = 1;
					}
				}

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return num;
		}
	}
}
