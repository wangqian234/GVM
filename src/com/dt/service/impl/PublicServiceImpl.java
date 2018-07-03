package com.dt.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dt.dao.BaseInfoDao;
import com.dt.dao.PublicDao;
import com.dt.entity.DetectorFacility;
import com.dt.entity.DetectorTriggerLog;
import com.dt.entity.EquipmentInfo;
import com.dt.entity.Project;
import com.dt.service.PublicService;


/**
 * 用户service
 */
@Service("PublicServiceImpl")
@Transactional
public class PublicServiceImpl implements PublicService {

	@Autowired
	private PublicDao publicDao;

	
	public List<DetectorFacility> selectFacilityList() {
		// TODO Auto-generated method stub
		
		List<Object> listSource = publicDao.selectFacilityList();
		
		Iterator<Object> it = listSource.iterator();
		List<DetectorFacility> listFacility = objToEquipmentInfo(it);
		return listFacility;
	}
	
	

	// List<Object>类型转换成List<DetectorFacility>
	private List<DetectorFacility> objToEquipmentInfo(Iterator<Object> it) {
		Object[] obj = null;
		DetectorFacility detectorFacility = null;

		List<DetectorFacility> listGoal = new ArrayList<DetectorFacility>();
		int i = 0;
		while (it.hasNext()) {
			i++;
			obj = (Object[]) it.next();
			detectorFacility = new DetectorFacility();
			detectorFacility.setDetector_Facility_Id(Integer.parseInt(obj[0].toString()));
			detectorFacility.setDetector_Facility_Name(obj[1].toString());
			detectorFacility.setDetector_Facility_Alias(obj[2].toString());
			detectorFacility.setDetector_Facility_State(Integer.parseInt(obj[3].toString()));
			listGoal.add(detectorFacility);
		}
		return listGoal;
	}



	public List<Project> selectProjectList() {
		// TODO Auto-generated method stub
		List<Object> listSource=publicDao.selectProjectList();
		Iterator<Object> it=listSource.iterator();
		List<Project> list=objToProject(it);
		return list;
	}
	
	// List<Object>类型转换成List<DetectorFacility>
		private List<Project> objToProject(Iterator<Object> it) {
			Object[] obj = null;
			Project project = null;

			List<Project> listGoal = new ArrayList<Project>();
			int i = 0;
			while (it.hasNext()) {
				i++;
				obj = (Object[]) it.next();
				project = new Project();
				project.setProject_Id(Integer.parseInt(obj[0].toString()));
				project.setProject_Name(obj[1].toString());
				listGoal.add(project);
			}
			return listGoal;
		}

}
