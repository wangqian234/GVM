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
 * @author ghl
 * @date   2017年9月26日
 */
@Service("/EvalStateService")
@Transactional
public class EvalStateServiceImpl implements EvalStateService{
	@Autowired
	EvalStateDao evalStateDao;
	//设备查询
	public List<DetectorEquipment> selectEquipment(Integer detector_Facility_Id) {
		List<Object> listSource = evalStateDao.findEquipment(detector_Facility_Id);		
		Iterator<Object> it = listSource.iterator();
		List<DetectorEquipment> listInfo = objToDetectorEquipment(it);
		return listInfo;
	}
	// List<Object>类型转换成List<DetectorEquipment>
	private List<DetectorEquipment> objToDetectorEquipment(Iterator<Object> it) {
		Object[] obj = null;
		DetectorEquipment detectorEquipment = null;
		List<DetectorEquipment> listGoal = new ArrayList<DetectorEquipment>();
		int i = 0;
		while (it.hasNext()) {
			i++;
			obj = (Object[]) it.next();
			detectorEquipment = new DetectorEquipment();			
			detectorEquipment.setDetector_Equipment_Name(obj[0].toString());
			detectorEquipment.setDetector_Equipment_Id(Integer.parseInt(obj[1].toString()));			
			listGoal.add(detectorEquipment);
		}
		return listGoal;		
	}
	
	//查询分析设备的数据(UseDate,lifeTime)
	public String analysisEquipment(Integer detector_Equipment_Id, String failare) {
		List<Object> listSource = evalStateDao.findEquipments(detector_Equipment_Id);
		Iterator<Object> it = listSource.iterator();		
		String listInfo = objTo(it,failare);
		return listInfo;
	}
	// List<Object>类型转换成List<DetectorEquipment>
	private String objTo(Iterator<Object> it, String failare){
		Object[] obj = null;
		obj = (Object[]) it.next();	
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  	    
	    Date getDate = new Date();
	    String Date = sdf.format(getDate);
	    Calendar  bef = Calendar.getInstance();
	    Calendar aft = Calendar.getInstance();
	    try {
	    	bef.setTime(sdf.parse(Date));
			aft.setTime(sdf.parse(obj[0].toString()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    int month,monthyear,monthday;
	    if(aft.get(Calendar.MONTH)<bef.get(Calendar.MONTH)){
	    	 month = bef.get(Calendar.MONTH)-aft.get(Calendar.MONTH);
	    }else{
	    	 month = aft.get(Calendar.MONTH)-bef.get(Calendar.MONTH);
	    }
	    if(aft.get(Calendar.YEAR)<bef.get(Calendar.YEAR)){
	    	 monthyear = (bef.get(Calendar.YEAR)-aft.get(Calendar.YEAR))*12;
	    }else{
	    	 monthyear = (aft.get(Calendar.YEAR)-bef.get(Calendar.YEAR))*12;
	    }
	    if(aft.get(Calendar.DATE)<bef.get(Calendar.DATE)){
	    	 monthday = (bef.get(Calendar.DATE)-aft.get(Calendar.DATE))/30;
	    }else{
	    	 monthday = (aft.get(Calendar.DATE)-bef.get(Calendar.DATE))/30;
	    }
	    int service = month + monthyear + monthday;
	    Double failarenum = Double.parseDouble(failare);
	    Integer lifeTime = Integer.parseInt(obj[1].toString());
	    Double life = 1-(service/(lifeTime*(1-failarenum)));	
	    Double Safety = 1-(failarenum*0.9);
	    Double Maintenance = failarenum*0.9;
	    Double Replacement = (life*0.6)+((1-Maintenance)*0.4);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("failarenum", failarenum);
        jsonObject.put("Safety", Safety);
        jsonObject.put("life", life);
        jsonObject.put("Maintenance", Maintenance);
        jsonObject.put("Replacement", Replacement);
        System.out.println("张群"+jsonObject.toString());
		return jsonObject.toString();
	}
	//查询分析设备的数据(Triggler)
	public String findEquipment(Integer detector_Equipment_Id) {
		List<Object> listSource = evalStateDao.findTrigglerlogs(detector_Equipment_Id);
		Double num = Double.parseDouble(listSource.get(0).toString());		
		Double  Failure = (6*num)/(24*30);		
		return Failure.toString();
	}


}
