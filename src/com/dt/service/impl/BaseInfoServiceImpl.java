package com.dt.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dt.dao.BaseInfoDao;
import com.dt.entity.DetectorTriggerLog;
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

	public List<EquipmentInfo> selectBaseList(String project, String facility) {
		// TODO Auto-generated method stub
		List<Object> listSource=null;
		if (project != "" && facility != "") {
			listSource = baseInfoDao.selectBaseList(Integer.parseInt(project), Integer.parseInt(facility));
		}

		Iterator<Object> it = listSource.iterator();
		List<EquipmentInfo> listInfo = objToEquipmentInfo(it);
		return listInfo;
	}

	// List<Object>类型转换成List<Equipment>
	private List<EquipmentInfo> objToEquipmentInfo(Iterator<Object> it) {
		Object[] obj = null;
		EquipmentInfo equipmentInfo = null;

		List<EquipmentInfo> listGoal = new ArrayList<EquipmentInfo>();
		int i = 0;
		while (it.hasNext()) {
			i++;
			obj = (Object[]) it.next();
			equipmentInfo = new EquipmentInfo();
			equipmentInfo.setEquipment_Id(Integer.parseInt(obj[0].toString()));
			equipmentInfo.setEquipment_No(obj[1].toString());
			equipmentInfo.setEquipment_Name(obj[2].toString());
			equipmentInfo.setEquipment_Room(obj[3].toString());
			equipmentInfo.setEquipment_Type(obj[4].toString());
			equipmentInfo.setEquipment_Project(obj[5].toString());
			listGoal.add(equipmentInfo);
		}
		return listGoal;
	}

	public List<Object> getbaseTotalRow(String project, String facility) {
		// TODO Auto-generated method stub
		List<Object> list = baseInfoDao.getbaseTotalRow(Integer.parseInt(project), Integer.parseInt(facility));
		return list;
	}

	public List<EquipmentInfo> findBaseList(String project, String facility, Integer offset, Integer limit) {
		// TODO Auto-generated method stub
		List<Object> listSource = baseInfoDao.findBaseList(Integer.parseInt(project), Integer.parseInt(facility),
				offset, limit);

		List<EquipmentInfo> listGoal = new ArrayList<EquipmentInfo>();
		for (int i = offset; i < limit + offset && i < listSource.size(); i++) {
			EquipmentInfo equipmentInfo = new EquipmentInfo();
			Object[] obj = (Object[]) listSource.get(i);
			equipmentInfo.setEquipment_Id(Integer.parseInt(obj[0].toString()));
			equipmentInfo.setEquipment_No(obj[1].toString());
			equipmentInfo.setEquipment_Name(obj[2].toString());
			equipmentInfo.setEquipment_Room(obj[3].toString());
			equipmentInfo.setEquipment_Type(obj[4].toString());
			listGoal.add(equipmentInfo);
		}
		return listGoal;
	}

	public EquipmentInfo selectEquipmentById(Integer equipmentId) {
		// TODO Auto-generated method stub
		List<Object> listSource = baseInfoDao.selectEquipmentById(equipmentId);
		Object[] obj = (Object[]) listSource.get(0);
		EquipmentInfo eInfo = new EquipmentInfo();
		eInfo.setEquipment_Id(Integer.parseInt(obj[0].toString()));
		eInfo.setEquipment_No(obj[1].toString());
		eInfo.setEquipment_Name(obj[2].toString());
		eInfo.setEquipment_State(obj[3].toString());
		eInfo.setEquipment_Type(obj[4].toString());
		eInfo.setEquipment_Room(obj[5].toString());
		eInfo.setEquipment_Version(obj[6].toString());
		eInfo.setEquipment_Price(obj[7].toString());
		eInfo.setEquipment_TP(obj[8].toString());
		eInfo.setEquipment_Manufacturers(obj[9].toString());
		eInfo.setEquipment_ProductionDate(obj[10].toString());
		eInfo.setEquipment_UseDate(obj[11].toString());
		eInfo.setEquipment_MaintenancePeriod(obj[12].toString());
		eInfo.setEquipment_RepairsDate(obj[13].toString());
		eInfo.setEquipment_Tel(obj[14].toString());
		eInfo.setEquipment_Mobile(obj[15].toString());
		eInfo.setEquipment_FactoryNo(obj[16].toString());
		eInfo.setEquipment_Lifetime(obj[17].toString());
		eInfo.setEquipment_Director(obj[18].toString());
		eInfo.setEquipment_UserID(obj[19].toString());
		eInfo.setEquipment_AddDate(obj[20].toString());
		eInfo.setEquipment_GUID(obj[21].toString());
		eInfo.setEquipment_Memo(obj[22].toString());
		return eInfo;
	}

}
