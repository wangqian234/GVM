package com.dt.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.dt.dao.EvalStateDao;
import com.dt.entity.DetectorEquipment;
import com.dt.service.EvalStateService;

import net.sf.json.JSONObject;

/**
 * 设备健康状态评估
 * 
 * @author ghl
 * @date 2017年9月26日
 */
@Service("/EvalStateService")
@Transactional
public class EvalStateServiceImpl implements EvalStateService {
	@Autowired
	EvalStateDao evalStateDao;

	// 设备查询
	public List<DetectorEquipment> selectEquipment(String project, String facility, int offset, int limit) {
		List<Object> listSource = evalStateDao.findEquipment(Integer.parseInt(project), Integer.parseInt(facility),
				offset, limit);
		List<DetectorEquipment> listGoal = new ArrayList<DetectorEquipment>();
		for (int i = offset; i < limit + offset && i < listSource.size(); i++) {
			DetectorEquipment detectorEquipment = new DetectorEquipment();
			Object[] obj = (Object[]) listSource.get(i);
			detectorEquipment.setDetector_Equipment_Id(Integer.parseInt(obj[0].toString()));
			detectorEquipment.setDetector_Equipment_No(obj[1].toString());
			detectorEquipment.setDetector_EquipmentRoom_name(obj[2].toString());
			detectorEquipment.setDetector_EquipmentType_name(obj[3].toString());
			detectorEquipment.setDetector_Equipment_Name(obj[4].toString());
			detectorEquipment.setDetector_EquipmentType_Id(obj[5].toString());
			listGoal.add(detectorEquipment);
		}
		return listGoal;
	}

	// 查询分析设备的数据(UseDate,lifeTime)
	public String analysisEquipment(Integer detector_Equipment_Id, String failare) {
		List<Object> listSource = evalStateDao.findEquipments(detector_Equipment_Id);
		Iterator<Object> it = listSource.iterator();
		String listInfo = objTo(it, failare);
		return listInfo;
	}

	// List<Object>类型转换成List<DetectorEquipment>
	private String objTo(Iterator<Object> it, String failare) {
		Object[] obj = null;
		obj = (Object[]) it.next();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date getDate = new Date();
		String Date = sdf.format(getDate);
		Calendar bef = Calendar.getInstance();
		Calendar aft = Calendar.getInstance();
		try {
			bef.setTime(sdf.parse(Date));
			aft.setTime(sdf.parse(obj[0].toString()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int month, monthyear, monthday;
		if (aft.get(Calendar.MONTH) < bef.get(Calendar.MONTH)) {
			month = bef.get(Calendar.MONTH) - aft.get(Calendar.MONTH);
		} else {
			month = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
		}
		if (aft.get(Calendar.YEAR) < bef.get(Calendar.YEAR)) {
			monthyear = (bef.get(Calendar.YEAR) - aft.get(Calendar.YEAR)) * 12;
		} else {
			monthyear = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
		}
		if (aft.get(Calendar.DATE) < bef.get(Calendar.DATE)) {
			monthday = (bef.get(Calendar.DATE) - aft.get(Calendar.DATE)) / 30;
		} else {
			monthday = (aft.get(Calendar.DATE) - bef.get(Calendar.DATE)) / 30;
		}
		int service = month + monthyear + monthday;
		Double failarenum = Double.parseDouble(failare);
		Integer lifeTime = Integer.parseInt(obj[1].toString());
		Double life;
		if (lifeTime == 0 || (1 - failarenum) == 0) {
			life = 0.0;
		} else {
			life = 1 - (service / (lifeTime * (1 - failarenum)));
		}
		Double Safety = 1 - (failarenum * 0.9);
		Double Maintenance = failarenum * 0.9;
		Double Replacement = 1 - ((life * 0.6) + ((1 - Maintenance) * 0.4));

		// 安全性能
		String conclude = "";
		String safetyStr = "设备的安全率为" + String.format("%.2f", Safety) + ",";// a为安全性能判断
		if (Safety < 0.50) {
			safetyStr += "安全系数较低，请尽快安排相关人员检修。";
			conclude = "综上，设备状态不佳，建议经常维护或者直接更换设备！";
		} else if (Safety < 0.70) {
			safetyStr += "安全系数一般，平时注意设备的维护和保养。";
			conclude = "综上，设备状态一般，建议不时的对设备运行状态进行查看，检修，保证正常的物业运作！！";
		} else if (Safety < 0.85) {
			safetyStr += "安全系数较高，设备可以放心使用。";
			conclude = "综上，设备状态良好，每隔两个月对设备进行健康维护即可。！";
		} else {
			safetyStr += "安全系数优良，设备状况好。";
			conclude = "综上，设备状态优良！";
		}
		// 故障率
		String failStr = "设备故障率为" + String.format("%.2f", failarenum) + ", ";
		if (failarenum < 0.1) {
			failStr += "运行状况优良，故障率低。";
		} else if (failarenum > 0.1 && failarenum < 0.3) {
			failStr += "属于正常范围，注意正常保养。";
		} else if (failarenum > 0.3 && failarenum < 0.6) {
			failStr += "故障率较低，请定时进行设备维护、检修。";
		} else {
			failStr += "故障率过低，请考虑替换相关零部件或更换设备。";
		}
		// 寿命
		int b;// b为预测寿命 年
		int c;// 月
		b = (int) ((lifeTime - service) * (1 - failarenum) / 12);
		c = (int) ((lifeTime - service) * (1 - failarenum) % 12);
		String lifeStr = "设备标准寿命为" + lifeTime + "月，剩余寿命为" + b + "年" + c + "个月。";
		// 更换概率
		String replaceStr = "设备的更换概率为" + String.format("%.2f", Replacement) + ",";
		if (Replacement > 0.7) {
			replaceStr += "更换概率过高，建议跟换设备。";
		} else if (0.5 < Replacement && Replacement < 0.7) {
			replaceStr += "更换概率较高，请注意定期维护设备。";
		} else if (0.2 < Replacement && Replacement < 0.5) {
			replaceStr += "设备更换概率在正常范围，请正常使用。";
		} else {
			replaceStr += "设备状态优良，请放心使用。";
		}
		// 维护成本
		String maintanceStr = "设备的维护率为" + String.format("%.2f", Maintenance) + ",";
		if (Maintenance > 0.7) {
			maintanceStr += "维护成本过高，请及时更换。";
		} else if (0.5 < Maintenance && Maintenance < 0.7) {
			maintanceStr += "维护成本过高较高，不建议继续使用。";
		} else if (0.2 < Maintenance && Maintenance < 0.5) {
			maintanceStr += "维护成本正常，请定期维护、保养。";
		} else {
			maintanceStr += "维护成本低，请放心使用，注意保养设备。";
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("failarenum", failarenum);
		jsonObject.put("Safety", Safety);
		jsonObject.put("life", life);
		jsonObject.put("Maintenance", Maintenance);
		jsonObject.put("Replacement", Replacement);
		jsonObject.put("resultFail", failStr);
		jsonObject.put("resultLife", lifeStr);
		jsonObject.put("resultReplace", replaceStr);
		jsonObject.put("resultMaintance", maintanceStr);
		jsonObject.put("resultSafe", safetyStr);
		jsonObject.put("conclude", conclude);
		return jsonObject.toString();
	}

	// 查询分析设备的数据(Triggler)
	public String findEquipment(Integer detector_Equipment_Id) {
		List<Object> listSource = evalStateDao.findTrigglerlogs(detector_Equipment_Id);
		Double num = Double.parseDouble(listSource.get(0).toString());
		Double Failure = (1.5 * num) / (24 * 30);
		return Failure.toString();
	}

	// 查询列表的总行数
	public List<Object> getbaseTotalRow(String project, String facility) {
		List<Object> list = evalStateDao.getbaseTotalRow(Integer.parseInt(project), Integer.parseInt(facility));
		return list;
	}

}
