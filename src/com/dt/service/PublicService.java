package com.dt.service;

import java.util.List;

import com.dt.entity.DetectorFacility;
import com.dt.entity.Project;

public interface PublicService {
	
	List<DetectorFacility> selectFacilityList();
	
	List<Project> selectProjectList();
}