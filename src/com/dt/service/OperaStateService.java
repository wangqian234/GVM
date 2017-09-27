package com.dt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dt.dao.OperaStateDao;

@Service("OperaStateService")
@Transactional
public interface OperaStateService {

	public List<Object> getbaseInfo(String project, String facility);
}
