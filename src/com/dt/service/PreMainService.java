package com.dt.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface PreMainService {

	List<Map<String, String>> selectEquipList();

	List<Map<String, String>> analyzeList() throws ParseException;

}
