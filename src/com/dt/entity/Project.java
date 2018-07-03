package com.dt.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Project implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer Project_Id;//设备id，主键
	private String Project_Name;//设备id，主键
	
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getProject_Id() {
		return Project_Id;
	}
	public void setProject_Id(Integer project_Id) {
		Project_Id = project_Id;
	}
	public String getProject_Name() {
		return Project_Name;
	}
	public void setProject_Name(String project_Name) {
		Project_Name = project_Name;
	}
}
