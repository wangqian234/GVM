package com.dt.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dt.dao.PreMainDao;
import com.dt.entity.DetectorEquipment;
import com.dt.entity.EquipmentInfo;
import com.dt.service.PreMainService;

@Service("PreMainService")
@Transactional
public class PreMainServiceImpl implements PreMainService{
	@Autowired
	private PreMainDao preMainDao;
	
	//设备list
	public List<EquipmentInfo> selectEquipList(){
		List<Object> listSource = preMainDao.selectEquipList();
		Iterator<Object> it = listSource.iterator();
		List<EquipmentInfo> listInfo = objToEquipmentInfo(it);
		return listInfo;
	}
	
	@SuppressWarnings("unused")
	private List<EquipmentInfo> objToEquipmentInfo(Iterator<Object> it) {
		Object[] obj = null;
		EquipmentInfo equipmentInfo = null;
		List<EquipmentInfo> listGoal = new ArrayList<EquipmentInfo>();
		int i = 0;
		while (it.hasNext()) {
			i++;
			obj = (Object[]) it.next();
			equipmentInfo = new EquipmentInfo();

			equipmentInfo.setEquipment_No(obj[0].toString());
			equipmentInfo.setEquipment_Name(obj[1].toString());
			listGoal.add(equipmentInfo);
		}
		return listGoal;
	}

	//分析list
	public List<DetectorEquipment> analyzeList(Integer preid) {
		// TODO 自动生成的方法存根
		return null;
	}

	
}
