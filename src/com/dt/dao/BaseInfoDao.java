package com.dt.dao;

import java.util.List;

import com.dt.entity.EquipmentInfo;

public interface BaseInfoDao {

	List<Object> selectBaseList();

	List<Object> getbaseTotalRow(Integer project, Integer facility);

	List<Object> findBaseList(Integer project, Integer facility, Integer offset, Integer limit);

	List<Object> selectEquipmentById(Integer equipmentId);
}
