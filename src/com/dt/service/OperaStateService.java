package com.dt.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("OperaStateService")
@Transactional
public interface OperaStateService {

	public String getbaseInfo(String project, String facility);
}
