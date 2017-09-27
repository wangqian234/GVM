package com.dt.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dt.dao.BaseInfoDao;
import com.dt.entity.EquipmentInfo;
import com.dt.service.BaseInfoService;

/**
 * 用户service
 */
@Service("BaseInfoService")
@Transactional
public class BaseInfoServiceImpl implements BaseInfoService {

	@Autowired
	private BaseInfoDao baseInfoDao;

	public List<EquipmentInfo> selectBaseList() {
		// TODO Auto-generated method stub

		List<Object> listSource = baseInfoDao.selectBaseList();

		Iterator<Object> it = listSource.iterator();
		List<EquipmentInfo> listInfo = objToEquipmentInfo(it);
		return listInfo;
	}

	// List<Object>类型转换成List<CheckHouse>
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
			equipmentInfo.setEquipment_Room(obj[2].toString());
			equipmentInfo.setEquipment_Type(obj[3].toString());
			
			listGoal.add(equipmentInfo);
		}
		return listGoal;
	}

}
