package com.dt.service;

import java.util.List;

import com.dt.entity.DetectorFacility;

public interface PublicService {
	
	List<DetectorFacility> selectFacilityList();
}