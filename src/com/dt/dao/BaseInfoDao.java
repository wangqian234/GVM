package com.dt.dao;

import java.util.List;

public interface BaseInfoDao {

	List<Object> selectBaseList(Integer project, Integer facility);

	List<Object> getbaseTotalRow(Integer project, Integer facility);

	List<Object> findBaseList(Integer project, Integer facility, Integer offset, Integer limit);

	List<Object> selectEquipmentById(Integer equipmentId);
}
