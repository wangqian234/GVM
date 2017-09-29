package com.dt.service;

import java.text.ParseException;
import java.util.List;

import com.dt.entity.PreInfo;


public interface PreMainService {

	List<PreInfo> selectEquipList();

	String analyzeList(Integer preId,Object[] obj) throws ParseException;

}
